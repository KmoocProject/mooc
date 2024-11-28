package net.fullstack7.mooc.service;

import net.fullstack7.mooc.domain.*;
import net.fullstack7.mooc.dto.CourseCreateDTO;
import net.fullstack7.mooc.dto.CourseDetailDTO;
import net.fullstack7.mooc.dto.LectureContentCreateDTO;
import net.fullstack7.mooc.dto.LectureContentDTO;
import net.fullstack7.mooc.dto.LectureContentUpdateDTO;
import net.fullstack7.mooc.dto.LectureCreateDTO;
import net.fullstack7.mooc.dto.LectureDTO;
import net.fullstack7.mooc.dto.LectureFileDTO;
import net.fullstack7.mooc.dto.LectureUpdateDTO;
import net.fullstack7.mooc.dto.QuizCreateDTO;
import net.fullstack7.mooc.dto.QuizDTO;
import net.fullstack7.mooc.util.FileUploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.fullstack7.mooc.repository.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강좌입다."));

    Lecture lecture = Lecture.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .course(course)
        .build();

    return lectureRepository.save(lecture);
  }

  public LectureContent createContent(LectureContentCreateDTO dto) throws IOException {
    Lecture lecture = lectureRepository.findByLectureId(dto.getLectureId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 섹션입니다."));

    if (("video".equals(dto.getType()) || "file".equals(dto.getType())) && dto.getFile() == null) {
      throw new IllegalArgumentException("파일은 필수입니다.");
    }

    LectureContent content = LectureContent.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .lecture(lecture)
        .isVideo("video".equals(dto.getType()) ? 1 : 0)
        .build();

    LectureContent savedContent = lectureContentRepository.save(content);

    switch (dto.getType()) {
      case "video", "file" -> {
        String filePath = "video".equals(dto.getType())
            ? fileUploadUtil.uploadVideoFile(dto.getFile(), "videos")
            : fileUploadUtil.uploadDocumentFile(dto.getFile(), "documents");
        saveLectureFile(content, dto.getFile(), filePath);
      }
      case "quiz" -> {
        if (dto.getQuizzes() == null || dto.getQuizzes().isEmpty()) {
          throw new IllegalArgumentException("최소 하나의 퀴즈는 필수입니다.");
        }
        content.setIsVideo(0);

        // 퀴즈 생성 및 연결
        dto.getQuizzes().forEach(quizDTO -> {
          Quiz quiz = Quiz.builder()
              .question(quizDTO.getQuestion())
              .answer(quizDTO.getAnswer())
              .build();
          content.addQuiz(quiz);
        });
      }
    }
    return savedContent;
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

  @Transactional
  public void updateLecture(int lectureId, LectureUpdateDTO dto) {
    Lecture lecture = lectureRepository.findById(lectureId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 섹션입니다."));

    lecture.setTitle(dto.getTitle());
    lecture.setDescription(dto.getDescription());

    lectureRepository.save(lecture);
  }

  @Transactional
  public void updateContent(int contentId, LectureContentUpdateDTO dto) throws IOException {
    LectureContent content = lectureContentRepository.findById(contentId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));

    content.setTitle(dto.getTitle());
    content.setDescription(dto.getDescription());

    switch (dto.getType()) {
      case "video", "file" -> {
        content.setIsVideo("video".equals(dto.getType()) ? 1 : 0);
        if (dto.getFile() != null) {
          LectureFile oldFile = lectureFileRepository.findByLectureContent(content)
              .orElse(null);
          if (oldFile != null) {
            fileUploadUtil.deleteFile(oldFile.getFilePath());
            lectureFileRepository.delete(oldFile);
          }

          String filePath = dto.getType().equals("video")
              ? fileUploadUtil.uploadVideoFile(dto.getFile(), "videos")
              : fileUploadUtil.uploadDocumentFile(dto.getFile(), "documents");
          saveLectureFile(content, dto.getFile(), filePath);
        }
      }
      case "quiz" -> {
        content.setIsVideo(0);
        // 기존 퀴즈들 모두 삭제
        quizRepository.deleteAllByLectureContent(content);

        // 새 퀴즈들 생성
        if (dto.getQuizzes() == null || dto.getQuizzes().isEmpty()) {
          throw new IllegalArgumentException("최소 하나의 퀴즈는 필수입니다.");
        }

        dto.getQuizzes().forEach(quizDTO -> {
          Quiz quiz = Quiz.builder()
              .question(quizDTO.getQuestion())
              .answer(quizDTO.getAnswer())
              .lectureContent(content)
              .build();
          quizRepository.save(quiz);
        });
      }
    }

    lectureContentRepository.save(content);
  }

  @Transactional
  public void deleteContent(int contentId) {
    try {
      LectureContent content = lectureContentRepository.findById(contentId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않 텐츠입니다."));

      // 연관된 파일이나 퀴즈 삭제
      LectureFile file = lectureFileRepository.findByLectureContent(content).orElse(null);
      if (file != null) {
        fileUploadUtil.deleteFile(file.getFilePath());
        lectureFileRepository.delete(file);
      }

      // 모든 퀴즈 삭제
      quizRepository.deleteAllByLectureContent(content);

      lectureContentRepository.delete(content);
    } catch (Exception e) {
      log.error("콘텐츠 삭제 실패", e);
      throw new RuntimeException("콘텐츠 삭제에 실패했습니다.", e);
    }
  }

  // 읽기 전용 트랜잭션 (CUD방지 및 내부최적화)
  @Transactional(readOnly = true)
  public CourseDetailDTO getCourseWithContents(int courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강좌입니다."));

    List<LectureDTO> lectureDTOs = course.getLectures().stream()
        .map(lecture -> {
          List<LectureContentDTO> contentDTOs = lecture.getContents().stream()
              .map(content -> {
                LectureContentDTO contentDTO = LectureContentDTO.builder()
                    .lectureContentId(content.getLectureContentId())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .isVideo(content.getIsVideo())
                    .build();

                // 비디오나 파일인 경우
                if (content.getIsVideo() == 1) {
                  lectureFileRepository.findByLectureContent(content)
                      .ifPresent(file -> contentDTO.setFile(
                          LectureFileDTO.builder()
                              .fileName(file.getFileName())
                              .filePath(file.getFilePath())
                              .fileExtension(file.getFileExtension())
                              .build()));
                } else {
                  // 퀴즈인 경우
                  List<Quiz> quizzes = quizRepository.findAllByLectureContent(content);
                  if (!quizzes.isEmpty()) {
                    contentDTO.setQuizzes(quizzes.stream()
                        .map(quiz -> QuizDTO.builder()
                            .quizId(quiz.getQuizId())
                            .question(quiz.getQuestion())
                            .answer(quiz.getAnswer())
                            .build())
                        .collect(Collectors.toList()));
                  }
                }
                return contentDTO;
              })
              .collect(Collectors.toList());

          return LectureDTO.builder()
              .lectureId(lecture.getLectureId())
              .title(lecture.getTitle())
              .description(lecture.getDescription())
              .contents(contentDTOs)
              .build();
        })
        .collect(Collectors.toList());

    return CourseDetailDTO.builder()
        .courseId(course.getCourseId())
        .title(course.getTitle())
        .description(course.getDescription())
        .thumbnail(course.getThumbnail())
        .weeks(course.getWeeks())
        .learningTime(course.getLearningTime())
        .language(course.getLanguage())
        .teacherId(course.getTeacher().getTeacherId())
        .lectures(lectureDTOs)
        .build();
  }
  // public Course findByCourseId(int courseId) {
  // return courseRepository.findByCourseId(courseId)
  // .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강좌입니다."));
  // }
}
