package net.fullstack7.mooc.service;

import net.fullstack7.mooc.domain.*;
import net.fullstack7.mooc.dto.CourseCreateDTO;
import net.fullstack7.mooc.dto.LectureContentCreateDTO;
import net.fullstack7.mooc.dto.LectureCreateDTO;
import net.fullstack7.mooc.dto.QuizDTO;
import net.fullstack7.mooc.repository.CourseRepository;
import net.fullstack7.mooc.repository.SubjectRepository;
import net.fullstack7.mooc.util.FileUploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.fullstack7.mooc.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

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
        Lecture lecture = lectureRepository.findById(dto.getLectureId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 섹션입니다."));

        // 콘텐츠 생성
        LectureContent content = LectureContent.builder()
            .title(dto.getTitle())
            .description(dto.getDescription())
            .lecture(lecture)
            .isVideo(dto.getIsVideo())
            .build();

        content = lectureContentRepository.save(content);

        // 파일이 있는 경우
        if (dto.getFile() != null) {
            String filePath;
            if (dto.getIsVideo() == 1) {
                filePath = fileUploadUtil.uploadVideoFile(dto.getFile(), "videos");
            } else {
                filePath = fileUploadUtil.uploadDocumentFile(dto.getFile(), "documents");
            }

            LectureFile lectureFile = LectureFile.builder()
                .fileName(dto.getFile().getOriginalFilename())
                .filePath(filePath)
                .fileExtension(FilenameUtils.getExtension(dto.getFile().getOriginalFilename()))
                .lectureContent(content)
                .build();

            lectureFileRepository.save(lectureFile);
        }

        // 퀴즈가 있는 경우
        if (dto.getQuizList() != null && !dto.getQuizList().isEmpty()) {
            for (QuizDTO quizDto : dto.getQuizList()) {
                Quiz quiz = Quiz.builder()
                    .question(quizDto.getQuestion())
                    .answer(quizDto.getAnswer())
                    .lectureContent(content)
                    .build();
                quizRepository.save(quiz);
            }
        }

        return content;
    }
}