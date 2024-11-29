package net.fullstack7.mooc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Lecture;
import net.fullstack7.mooc.domain.Subject;
import net.fullstack7.mooc.dto.LectureCreateDTO;
import net.fullstack7.mooc.dto.LectureResponseDTO;
import net.fullstack7.mooc.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class CommonApiController {
    private final SubjectService subjectService;

    @PostMapping("/subject")
    public ResponseEntity<?> headerSubject() {
        try {
            List<Subject> subject = subjectService.getAllSubjects();
            return ResponseEntity.ok(subject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
