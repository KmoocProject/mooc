package net.fullstack7.mooc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.dto.CourseDetailDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.fullstack7.mooc.service.CourseService;
import net.fullstack7.mooc.service.CourseEnrollmentServiceIf;
import jakarta.servlet.http.HttpSession;
import net.fullstack7.mooc.dto.MemberDTO;
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/learn")
public class LearnController {
    private final CourseService courseService;
    private final CourseEnrollmentServiceIf courseEnrollmentService;

    @GetMapping("/view/{courseId}")
    public String getCourseDetail(@PathVariable int courseId, Model model, HttpSession session,
            RedirectAttributes redirectAttributes) {
        MemberDTO member = (MemberDTO) session.getAttribute("memberDTO");
        if(member == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login/login";
        }
        if(!courseEnrollmentService.checkAuthority(member.getMemberId(), courseId)) {
            redirectAttributes.addFlashAttribute("error", "권한이 없습니다. 수강신청 후 이용해주세요.");
            return "redirect:/main/main";
        }
        CourseDetailDTO courseDetail = courseService.getCourseWithContents(courseId);

        model.addAttribute("course", courseDetail);
        return "learn/view";
    }
}
