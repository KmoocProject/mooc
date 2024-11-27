package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.service.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MemberServiceImpl memberServiceImpl;

    @GetMapping("/myclass")
    public String mypage() {
        return "mypage/myclass";
    }

    @GetMapping("/memberView")
    public String memberView() {
        return "mypage/memberView" ;
    }

    @PostMapping("/memberModify")
    public String modifyMember(@Valid MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        return "redirect:/mypage/memberView";
    }


//    @GetMapping("")

    @PostMapping("/memberDelete")
    public String deleteMember(@RequestParam String memberId, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("탈퇴처리시작");
        memberServiceImpl.deleteMember(memberId);
        redirectAttributes.addFlashAttribute("errors", "탈퇴가 완료되었습니다.");
        session.invalidate();

        return "redirect:/main/main";
    }
}
