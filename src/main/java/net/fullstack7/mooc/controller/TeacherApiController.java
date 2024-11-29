package net.fullstack7.mooc.controller;


import jakarta.validation.Path;
import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Lecture;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.*;
import net.fullstack7.mooc.service.CourseService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;  
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;
import net.fullstack7.mooc.util.FileUploadUtil;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
@Log4j2
public class TeacherApiController {
    
    private final CourseService courseService;
    // private final QuizRepository quizRepository;
    // private final FileUploadUtil fileUploadUtil;

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

    @PutMapping("/lectures/{lectureId}")
    public ResponseEntity<ApiResponse<Void>> updateLecture(
            @PathVariable int lectureId,
            @RequestBody LectureUpdateDTO dto) {
        try {
            courseService.updateLecture(lectureId, dto);
            return ResponseEntity.ok(ApiResponse.success("섹션이 성공적으로 수정되었습니다."));
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
            return ResponseEntity.ok(ApiResponse.success("콘텐츠가 성공적으로 수정되었습니다."));
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

    @DeleteMapping("/lectures/{lectureId}/quizzes")
    public ResponseEntity<ApiResponse<Void>> deleteQuizzes(@PathVariable int lectureId) {
        try {
            courseService.deleteQuizzes(lectureId);
            return ResponseEntity.ok(ApiResponse.success("퀴즈가 성공적으로 삭제되었습니다."));
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

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filePath) {
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        return fileUploadUtil.downloadFile(filePath);
    }
}
