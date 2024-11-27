package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.dto.MemberModifyDTO;
import net.fullstack7.mooc.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final MemberServiceImpl memberServiceImpl;

    @GetMapping("/myclass")
    public String mypage(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        if (memberDTO == null) {
            return "redirect:/login/login";
        }
        model.addAttribute("member", memberDTO);
        return "mypage/myclass";
    }

    @GetMapping("/memberView")
    public String memberView(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        if(memberDTO != null) {
            model.addAttribute("member", memberDTO);
        } else {
            return "redirect:/login/login";
        }

        return "mypage/memberView" ;
    }

    @GetMapping("/creditTransform")
    public String creditTransform() {
        return "mypage/creditTransform" ;
    }
    @GetMapping("/creditTransform2")
    public String creditTransform2() {
        return "mypage/creditTransform2" ;
    }
    @GetMapping("/creditTransform3")
    public String creditTransform3() {
        return "mypage/creditTransform3" ;
    }
    @GetMapping("/creditclass")
    public String creditclass() {
        return "mypage/creditclass" ;
    }

//    @GetMapping("/memberModify")
//    public String memberModify(Model model, HttpSession session) {
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
//
//        if(memberDTO == null) {
//            return "redirect:/login/login";
//        }
//        MemberModifyDTO memberModifyDTO = (MemberModifyDTO) session.getAttribute("memberModifyDTO");
//        model.addAttribute("member", memberModifyDTO);
//        return "mypage/memberView" ;
//    }

    @PostMapping("/memberModify")
    public String modifyMember(@Valid MemberModifyDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        System.out.println("POST 요청 받음");
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("member", memberDTO);
            return "mypage/memberView" ;
        }
        try{ if(memberDTO != null) {
                memberServiceImpl.modifyMember(memberDTO);
        }
//            redirectAttributes.addFlashAttribute("successMessage", "회원 정보가 수정되었습니다.");
            session.setAttribute("successMessage", "회원 정보가 수정되었습니다.");
        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("errorMessage", "회원 정보 수정에 실패했습니다.");
            session.setAttribute("errorMessage", "회원 정보 수정에 실패했습니다.");
            return "redirect:/mypage/memberView";
        }

        return "redirect:/mypage/myclass";
    }





    @PostMapping("/memberDelete")
    public String deleteMember(@RequestParam String memberId, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("회원 탈퇴 요청, memberId: {}", memberId);
        memberServiceImpl.deleteMember(memberId);
        redirectAttributes.addFlashAttribute("errors", "탈퇴가 완료되었습니다.");
        session.invalidate();

        return "redirect:/main/main";
    }
}
