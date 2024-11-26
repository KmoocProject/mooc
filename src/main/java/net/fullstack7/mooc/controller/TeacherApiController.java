package net.fullstack7.mooc.controller;


import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Lecture;
import net.fullstack7.mooc.domain.LectureContent;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.CourseCreateDTO;
import net.fullstack7.mooc.dto.LectureContentCreateDTO;
import net.fullstack7.mooc.dto.LectureCreateDTO;
import net.fullstack7.mooc.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.fullstack7.mooc.dto.CourseResponseDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;  

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
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
            return ResponseEntity.ok(lectures);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/courses/{courseId}/contents")
    public ResponseEntity<?> createContents(
            @PathVariable Integer courseId,
            @ModelAttribute LectureContentCreateDTO contentDTO) {
        try {
            LectureContent content = courseService.createContent(contentDTO);
            return ResponseEntity.ok(content);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}