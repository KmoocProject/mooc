package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.fullstack7.mooc.dto.CourseDTO;
import net.fullstack7.mooc.dto.CourseSearchDTO;
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
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/list/{type}")
    public String courseList(@PathVariable String type, Model model
            , @Valid CourseSearchDTO searchDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            searchDTO = CourseSearchDTO.builder()
                    .institutionId(searchDTO.getInstitutionId())
                    .subjectId(searchDTO.getSubjectId())
                    .searchValue(searchDTO.getSearchValue())
                    .isCreditBank(searchDTO.getIsCreditBank())
                    .status(searchDTO.getStatus())
                    .build();
        }

        if(type.equals("credit")) searchDTO.setIsCreditBank(1);
        if(type.equals("open")) searchDTO.setIsCreditBank(0);
        if(type.equals("all")) searchDTO.setIsCreditBank(-1);

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
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        if(courseDTO == null) {
            redirectAttributes.addFlashAttribute("errors","존재하지 않는 강의입니다.");
            return "redirect:/course/list/all";
        }
        model.addAttribute("courseDTO", courseDTO);
        return "course/view";
    }
}
