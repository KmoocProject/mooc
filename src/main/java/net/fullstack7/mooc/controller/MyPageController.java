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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

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

//    @PostMapping("/memberDelete")
//    public String deleteMember(@RequestParam String memberId, RedirectAttributes redirectAttributes, HttpSession session) {
//        log.info("회원 탈퇴 요청, memberId: {}", memberId);
//        try {
//            memberServiceImpl.deleteMember(memberId);
//            session.invalidate();
//            redirectAttributes.addFlashAttribute("errors", "탈퇴가 완료되었습니다.");
//        } catch (Exception e) {
//            log.error("회원 탈퇴 처리 중 오류 발생, memberId: {}", memberId, e);
//            redirectAttributes.addFlashAttribute("errorMessage", "회원 탈퇴 처리 중 오류가 발생했습니다.");
//        }
//        return "redirect:/login/login";
//    }

    @PostMapping("/memberDelete")
    @ResponseBody
    public Map<String, Object> deleteMember(@RequestParam String memberId, HttpSession session) {
        log.info("회원 탈퇴 요청, memberId: {}", memberId);
        Map<String, Object> response = new HashMap<>();
        try {
            memberServiceImpl.deleteMember(memberId);
            session.invalidate();
            response.put("success", true);
            response.put("message", "탈퇴가 완료되었습니다.");
//            return ResponseEntity.ok(response);  // 성공 응답
        } catch (Exception e) {
            log.error("회원 탈퇴 처리 중 오류 발생, memberId: {}", memberId, e);
            response.put("success", false);
            response.put("message", "회원 탈퇴 처리 중 오류가 발생했습니다.");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 실패 응답
        }
        return response;
    }


}
