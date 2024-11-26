package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.AdminLoginDTO;
import net.fullstack7.mooc.dto.AdminSearchDTO;
import net.fullstack7.mooc.service.AdminServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceIf adminService;

    @GetMapping("/login")
    public String loginGet(HttpSession session, RedirectAttributes redirectAttributes) {
        if(session.getAttribute("loginAdminId") != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인했습니다.");
            return "redirect:/admin/main";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid AdminLoginDTO adminLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes
            , HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/login";
        }
        String result = adminService.login(adminLoginDTO);
        if (result == null) {
            redirectAttributes.addFlashAttribute("errors", "비밀번호가 일치하지 않거나 존재하지 않는 회원입니다.");
            return "redirect:/admin/login";
        }
        session.setAttribute("loginAdminId", result);
        return "redirect:/admin/main";
    }

    @GetMapping("/logout")
    public String logoutGet(HttpSession session) {
        session.removeAttribute("loginAdminId");
        return "redirect:/admin/login";
    }

    @GetMapping("/main")
    public String mainGet() {
        return "admin/main";
    }

    @GetMapping("/memberList")
    public String memberListGet(Model model, @Valid AdminSearchDTO adminSearchDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            adminSearchDTO = AdminSearchDTO.<Teacher>builder().build();
        }

        if(adminSearchDTO.getTypeSelect().equals("t"))
            model.addAttribute("pageinfo", adminService.getTeachers(adminSearchDTO));
        else
            model.addAttribute("pageinfo", adminService.getMembers(adminSearchDTO));

        model.addAttribute("searchinfo", adminSearchDTO);

        return "admin/memberList";
    }

}
