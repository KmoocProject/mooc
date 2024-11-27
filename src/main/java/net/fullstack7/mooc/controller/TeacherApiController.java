package net.fullstack7.mooc.controller;


import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Lecture;
import net.fullstack7.mooc.domain.LectureContent;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.*;
import net.fullstack7.mooc.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;  

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
@Log4j2
public class TeacherApiController {
    
    private final CourseService courseService;

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
            LectureContent content = null;
            content = courseService.createContent(dto);
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
}