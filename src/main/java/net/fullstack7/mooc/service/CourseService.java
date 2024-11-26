package net.fullstack7.mooc.service;

import net.fullstack7.mooc.domain.*;
import net.fullstack7.mooc.dto.CourseCreateDTO;
import net.fullstack7.mooc.dto.LectureContentCreateDTO;
import net.fullstack7.mooc.dto.LectureCreateDTO;
import net.fullstack7.mooc.repository.CourseRepository;
import net.fullstack7.mooc.repository.SubjectRepository;
import net.fullstack7.mooc.util.FileUploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.fullstack7.mooc.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CourseService {
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final LectureContentRepository lectureContentRepository;
    private final LectureFileRepository lectureFileRepository;
    private final QuizRepository quizRepository;
    private final SubjectRepository subjectRepository;
    private final FileUploadUtil fileUploadUtil;

    public Course createCourse(CourseCreateDTO dto, Teacher teacher) throws IOException {
        // 과목 조회
        Subject subject = subjectRepository.findBySubjectId(dto.getSubjectId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목입니다."));

        // 썸네일 업로드
        String thumbnailPath = fileUploadUtil.uploadImageFile(dto.getThumbnail(), "thumbnails");

        Course course = Course.builder()
            .title(dto.getTitle())
            .subject(subject)
            .weeks(dto.getWeeks())
            .learningTime(dto.getLearningTime())
            .language(dto.getLanguage())
            .description(dto.getDescription())
            .isCreditBank(dto.getIsCreditBank())
            .thumbnail(thumbnailPath)
            .teacher(teacher)
            .status("DRAFT")
            .viewCount(0)
            .createdAt(LocalDateTime.now())
            .build();

        return courseRepository.save(course);
    }

    public Lecture createLecture(LectureCreateDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강좌입니다."));

        Lecture lecture = Lecture.builder()
            .title(dto.getTitle())
            .description(dto.getDescription())
            .course(course)
            .build();

        return lectureRepository.save(lecture);
    }

    public LectureContent createContent(LectureContentCreateDTO dto) throws IOException {
        // 1. Lecture 조회
        Lecture lecture = lectureRepository.findByLectureId(dto.getLectureId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 섹션입니다."));

        // 2. 파일 유효성 검사
        if (("video".equals(dto.getType()) || "file".equals(dto.getType())) && dto.getFile() == null) {
            throw new IllegalArgumentException("파일은 필수입니다.");
        }

        // 3. LectureContent 생성
        LectureContent content = LectureContent.builder()
            .title(dto.getTitle())
            .description(dto.getDescription())
            .lecture(lecture)
            .isVideo("video".equals(dto.getType()) ? 1 : 0)
            .build();

        content = lectureContentRepository.save(content);

        // 4. 타입별 처리
        try {
            switch (dto.getType()) {
                case "video" -> {
                    String filePath = fileUploadUtil.uploadVideoFile(dto.getFile(), "videos");
                    saveLectureFile(content, dto.getFile(), filePath);
                    log.info("동영상 파일 업로드 완료: {}", filePath);
                }
                case "file" -> {
                    String filePath = fileUploadUtil.uploadDocumentFile(dto.getFile(), "documents");
                    saveLectureFile(content, dto.getFile(), filePath);
                    log.info("문서 파일 업로드 완료: {}", filePath);
                }
                case "quiz" -> {
                    Quiz quiz = Quiz.builder()
                        .question(dto.getQuestion())
                        .answer(dto.getAnswer())
                        .lectureContent(content)
                        .build();
                    quizRepository.save(quiz);
                    log.info("퀴즈 생성 완료: {}", quiz.getQuizId());
                }
                default -> throw new IllegalArgumentException("잘못된 콘텐츠 타입입니다.");
            }
        } catch (IOException e) {
            log.error("파일 업로드 실패", e);
            throw new RuntimeException("파일 업로드에 실패했습니다.", e);
        }

        return content;
    }

    private void saveLectureFile(LectureContent content, MultipartFile file, String filePath) {
        LectureFile lectureFile = LectureFile.builder()
            .fileName(file.getOriginalFilename())
            .filePath(filePath)
            .fileExtension(FilenameUtils.getExtension(file.getOriginalFilename()))
            .lectureContent(content)
            .build();
        
        lectureFileRepository.save(lectureFile);
        log.info("강의 파일 정보 저장 완료: {}", lectureFile.getLectureFileId());
    }
}