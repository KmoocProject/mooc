package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.CourseEnrollment;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.dto.*;
import net.fullstack7.mooc.service.CourseEnrollmentServiceIf;
import net.fullstack7.mooc.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
@Log4j2
public class CourseController {
    private final CourseService courseService;
    private final CourseEnrollmentServiceIf courseEnrollmentService;
    @GetMapping("/list/{type}")
    public String courseList(@PathVariable String type, Model model
            , @Valid CourseSearchDTO searchDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            searchDTO = CourseSearchDTO.builder()
                    .institutionId(searchDTO.getInstitutionId())
                    .subjectId(searchDTO.getSubjectId())
                    .searchValue(searchDTO.getSearchValue())
                    .isCreditBank(searchDTO.getIsCreditBank())
                    .status("PUBLISHED")
                    .build();
        }

        if(type.equals("credit")) searchDTO.setIsCreditBank(1);
        if(type.equals("open")) searchDTO.setIsCreditBank(0);
        if(type.equals("all")) searchDTO.setIsCreditBank(-1);
        searchDTO.setStatus("PUBLISHED");
        
        searchDTO.initialize();

        model.addAttribute("list", courseService.getCourses(searchDTO));
        model.addAttribute("type", type);
        model.addAttribute("pageDTO", searchDTO);
        model.addAttribute("institutions", courseService.getInstitutions());
        model.addAttribute("subjects", courseService.getSubjects());

        return "course/list";
    }

    @GetMapping("/view")
    public String courseView(@RequestParam int courseId, HttpSession session, Model model,RedirectAttributes redirectAttributes) {
        CourseViewDTO courseViewDTO = courseService.getCourseViewById(courseId);
        if(courseViewDTO == null) {
            redirectAttributes.addFlashAttribute("errors","존재하지 않는 강의입니다.");
            return "redirect:/course/list/all";
        }

        MemberDTO memberDTO = (MemberDTO)session.getAttribute("memberDTO");
        if(memberDTO == null) {
            return "redirect:/login/login";
        }
        CourseEnrollmentDTO courseEnrollmentDTO = courseEnrollmentService.isEnrolled(
                CourseEnrollmentDTO.builder()
                        .course(Course.builder().courseId(courseId).build())
                        .member(Member.builder().memberId(memberDTO.getMemberId()).build())
                        .build()
        );

        log.info("courseViewDTO : {}", courseViewDTO);
        model.addAttribute("courseViewDTO", courseViewDTO);
        model.addAttribute("isEnrolled", courseEnrollmentDTO);
        return "course/view";
    }
}
