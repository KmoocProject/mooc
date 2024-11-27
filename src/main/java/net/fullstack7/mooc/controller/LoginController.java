package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.mapper.MemberMapper;
import net.fullstack7.mooc.repository.MemberRepository;
import net.fullstack7.mooc.service.MemberServiceIf;
import net.fullstack7.mooc.util.CookieUtil;
import net.fullstack7.mooc.util.JSFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController extends HttpServlet {
    @Autowired
    private final MemberServiceIf memberService;
    private CookieUtil cUtil = new CookieUtil();
    private JSFunc JSFunc = new JSFunc();
    private MemberRepository memberRepository;
    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/findId")
    public String findId() {
        return "login/findId";
    }

    @GetMapping("/login")
    public String login(HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        if (session.getAttribute("memberId") != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인된 상태입니다. 회원가입 페이지에 접근할 수 없습니다.");
            return "redirect:/main/main";
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("memberId") String memberId, @RequestParam("password") String password, HttpSession session, Model model) {
        MemberDTO memberDTO = memberService.login(memberId, password);
        if (memberDTO != null) {
            session.setAttribute("memberId", memberDTO.getMemberId());
            session.setAttribute("password", memberDTO.getPassword());

            String redirectURL = (String) session.getAttribute("redirectAfterLogin");
            if (redirectURL != null) {
//                try {
//                    session.removeAttribute("redirectAfterLogin");
//                    redirectURL = URLDecoder.decode(URLDecoder.decode(redirectURL, "UTF-8"), "UTF-8");
//                    log.info("redirectURL:" + redirectURL);
//                    if (redirectURL.contains("login/regist") || redirectURL.contains("login/login")) {
//                        return "redirect:/";
//                    }
//                    return "redirect:" + URLDecoder.decode(URLDecoder.decode(redirectURL, "UTF-8"), "UTF-8");
//                } catch (Exception e) {
//                    log.error(e);
//                }
                session.removeAttribute("redirectAfterLogin");
                return "redirect:" + redirectURL;
            }
                return "redirect:/main/main";
            } else {
                model.addAttribute("errors", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return "login/login";
            }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("errors", "로그인에 실패하였습니다.");
        return "redirect:/main/main";
    }

    @GetMapping("/memberterms")
    public String memberterms(HttpSession session, RedirectAttributes redirectAttributes) {
        String loginCheck = (String) session.getAttribute("memberId");
        if (loginCheck != null) {
            redirectAttributes.addFlashAttribute("errors", "로그인 한 회원은 접근할 수 없습니다.");
            return "redirect:/main/main";
        }
        session.removeAttribute("termsAgree");
        return "login/memberterms";
    }

    @PostMapping("/memberterms")
    public String membertermsPost(@RequestParam(value="termsAgreement", defaultValue = "false" )boolean termsAgreement, HttpSession session, Model model) {
        if(termsAgreement){
            session.setAttribute("termsAgree", true);
            return "redirect:/login/regist";
        } else{
            model.addAttribute("errors", "약관동의 후 회원가입이 가능합니다.");
            return "redirect:/login/memberterms";
        }
    }

    @GetMapping("/regist")
    public String regist(HttpSession session, HttpServletResponse res, Model model, RedirectAttributes redirectAttributes) throws IOException {
        Boolean termsAgree = (Boolean) session.getAttribute("termsAgree");
        String loginCheck = (String) session.getAttribute("memberId");
        if (loginCheck != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인 한 회원은 접근할 수 없습니다.");
            return "redirect:/main/main";
        }
        return "login/regist";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public Map<String, Object> checkId(@RequestParam("memberId")String memberId){
        Map<String, Object> response = new HashMap<>();
        log.info("memberId"+memberId);
        String result = memberMapper.memberIdCheck(memberId);
        if(result == null){
            response.put("isAvailable", true);
            log.info("아이디사용가능");
        } else {
            response.put("isAvailable", false);
            log.info("아이디사용불가");
        }
        log.info("response"+response);
        return response;
    }


    @PostMapping("/regist")
    public String regist(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
//        session.setAttribute("termsAgree", true);

        if(bindingResult.hasErrors()) {
//            log.info("유효성 검사 오류: " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("memberDTO", memberDTO);
            return "redirect:/login/regist";
        }
        int result = memberService.registMember(memberDTO);

        if(result >0){
            redirectAttributes.addFlashAttribute("errors","회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
            return "redirect:/login/login";
        }else {
            redirectAttributes.addFlashAttribute("errors", "회원가입에 실패했습니다.");
            return "redirect:/login/regist";
        }
    }


    @GetMapping("/memberchose")
    public String memberchose() {
        return "login/memberchose";
    }



    @GetMapping("/finishjoin")
    public String finishjoin() {
        return "login/finishjoin";
    }

}