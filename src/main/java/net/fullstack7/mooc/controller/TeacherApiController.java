package net.fullstack7.mooc.controller;

import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Lecture;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.*;
import net.fullstack7.mooc.service.CourseService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.util.FileUploadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
@Log4j2
public class TeacherApiController {

    private final CourseService courseService;
    private final FileUploadUtil fileUploadUtil;

    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(
            @Valid CourseCreateDTO courseDTO,
            HttpSession session) {
        try {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            }

            Course course = courseService.createCourse(courseDTO, teacher);

            return ResponseEntity.ok(CourseResponseDTO.from(course));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(
            @PathVariable int courseId,
            @ModelAttribute CourseUpdateDTO dto,
            HttpSession session) {
        try {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            courseService.updateCourse(courseId, dto, teacher);
            return ResponseEntity.ok(ApiResponse.success("강좌가 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/courses/{courseId}/sections")
    public ResponseEntity<?> createSections(
            @PathVariable Integer courseId,
            @RequestBody List<LectureCreateDTO> sections) {
        try {
            List<Lecture> lectures = new ArrayList<>();
            for (LectureCreateDTO section : sections) {
                section.setCourseId(courseId);
                lectures.add(courseService.createLecture(section));
            }
            List<LectureResponseDTO> responseDTOs = lectures.stream()
                    .map(LectureResponseDTO::from)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/lectures/{lectureId}/contents")
    public ResponseEntity<ApiResponse<Void>> createContent(
            @PathVariable int lectureId,
            @ModelAttribute LectureContentCreateDTO dto) {
        try {
            dto.setLectureId(lectureId);
            courseService.createContent(dto);
            return ResponseEntity.ok(ApiResponse.success("콘텐츠가 성공적으로 생성되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/lectures/{lectureId}/contents/{contentId}")
    public ResponseEntity<ApiResponse<Void>> deleteContent(
            @PathVariable int lectureId,
            @PathVariable int contentId) {
        try {
            courseService.deleteContent(contentId);
            return ResponseEntity.ok(ApiResponse.success("콘텐츠가 성공적으로 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/lectures/{lectureId}/quizzes")
    public ResponseEntity<ApiResponse<Void>> createQuizzes(
            @PathVariable int lectureId,
            @RequestBody QuizCreateDTO dto) {
        try {
            dto.setLectureId(lectureId);
            courseService.createQuizzes(dto);
            return ResponseEntity.ok(ApiResponse.success("퀴즈가 성공적으로 생성되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/lectures/{lectureId}/quizzes/{quizId}")
    public ResponseEntity<ApiResponse<Void>> updateQuiz(
            @PathVariable int lectureId,
            @PathVariable int quizId,
            @RequestBody QuizDTO dto) {
        try {
            courseService.updateQuiz(quizId, dto);
            return ResponseEntity.ok(ApiResponse.success("퀴즈가 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/lectures/{lectureId}/quizzes/{quizId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuiz(
            @PathVariable int lectureId,
            @PathVariable int quizId) {
        try {
            courseService.deleteQuiz(quizId);
            return ResponseEntity.ok(ApiResponse.success("퀴즈가 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filePath) {
        log.debug("다운로드 요청 파일 경로: {}", filePath);
        return fileUploadUtil.downloadFile(filePath);
    }

    @PostMapping("/courses/{courseId}/lectures")
    public ResponseEntity<ApiResponse<Void>> createLecture(
            @PathVariable int courseId,
            @RequestBody LectureCreateDTO dto) {
        try {
            dto.setCourseId(courseId);
            courseService.createLecture(dto);
            return ResponseEntity.ok(ApiResponse.success("섹션이 추가되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/lectures/{lectureId}")
    public ResponseEntity<ApiResponse<Void>> deleteLecture(@PathVariable int lectureId) {
        try {
            courseService.deleteLecture(lectureId);
            return ResponseEntity.ok(ApiResponse.success("섹션이 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/lectures/{lectureId}")
    public ResponseEntity<ApiResponse<Void>> updateLecture(
            @PathVariable int lectureId,
            @RequestBody LectureUpdateDTO dto) {
        try {
            courseService.updateLecture(lectureId, dto);
            return ResponseEntity.ok(ApiResponse.success("섹션이 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/lectures/{lectureId}/contents/{contentId}")
    public ResponseEntity<ApiResponse<Void>> updateContent(
            @PathVariable int lectureId,
            @PathVariable int contentId,
            @ModelAttribute LectureContentUpdateDTO dto) {
        try {
            courseService.updateContent(contentId, dto);
            return ResponseEntity.ok(ApiResponse.success("콘텐츠가 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

}
