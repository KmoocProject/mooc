package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.service.MemberServiceIf;
import net.fullstack7.mooc.util.CookieUtil;
import net.fullstack7.mooc.util.JSFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Log4j2
public class LoginController extends HttpServlet {
    @Autowired
    private final MemberServiceIf memberService;
    private CookieUtil cUtil = new CookieUtil();
    private JSFunc JSFunc = new JSFunc();

    @GetMapping("/login")
    public String login(HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        if (session.getAttribute("memberId") != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인된 상태입니다. 회원가입 페이지에 접근할 수 없습니다.");
            return "redirect:/main/main";
        }
//        String referer = request.getHeader("referer");
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("memberId") String memberId, @RequestParam("password") String password, HttpSession session, Model model) {
        MemberDTO memberDTO = memberService.login(memberId, password);
        if (memberDTO != null) {
            session.setAttribute("memberId", memberDTO.getMemberId());
            log.info("id"+memberDTO.getMemberId());
            session.setAttribute("password", memberDTO.getPassword());
            log.info("pwd"+memberDTO.getPassword());

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

    //회원가입처리하고 열어서 약관만들어
//    @GetMapping("/registCheck")
//    public String registCheck(HttpServletResponse res, HttpSession session, RedirectAttributes redirectAttributes) {
//        String loginCheck = (String) session.getAttribute("memberId");
//        if (loginCheck != null) {
//            redirectAttributes.addFlashAttribute("errors", "로그인 한 회원은 접근할 수 없습니다.");
//            return "redirect:/main/main";
//        }
////        session.removeAttribute("termsAgree");
//        return "login/registCheck";
//    }
//
//    @PostMapping("registCheck")
//    public String registCheck(@RequestParam(value="termsAgreement", defaultValue = "false" )boolean termsAgreement, HttpSession session, Model model) {
//        if(termsAgreement){
//            session.setAttribute("termsAgree", true);
//            return "redirect:/login/regist";
//        } else{
//            model.addAttribute("errors", "약관동의 후 회원가입이 가능합니다.");
//            return "redirect:/login/registCheck";
//        }
//    }

    @GetMapping("/regist")
    public String regist(HttpSession session, HttpServletResponse res, Model model, RedirectAttributes redirectAttributes) throws IOException {
        Boolean termsAgree = (Boolean) session.getAttribute("termsAgree");
        String loginCheck = (String) session.getAttribute("memberId");
        if (loginCheck != null) {
            redirectAttributes.addFlashAttribute("errors", "이미 로그인 한 회원은 접근할 수 없습니다.");
            log.info("로그인함");
            return "redirect:/main/main";
        }
//        if(termsAgree == null || ! termsAgree){
//            model.addAttribute("erros", "약관에 동의한 후 회원가입이 가능합니다.");
//            return "login/registCheck";
//        }
//        session.removeAttribute("termsAgree");
        return "login/regist";
    }

    @PostMapping("/regist")
    public String regist(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
//        session.setAttribute("termsAgree", true);
        log.info("로그찍힘4");

        if(bindingResult.hasErrors()) {
            log.info("유효성 검사 오류: " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("memberDTO", memberDTO);
            return "redirect:/login/regist";
        }
        log.info("memberDTO"+memberDTO);
        int result = memberService.registMember(memberDTO);

        if(result >0){
            log.info("memberDTO성공"+memberDTO);
            
            redirectAttributes.addFlashAttribute("errors","회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
            return "redirect:/login/login";
        }else {
            log.info("memberDTO실패"+memberDTO);
            
            redirectAttributes.addFlashAttribute("errors", "회원가입에 실패했습니다.");
            return "redirect:/login/regist";
        }
    }


    @GetMapping("/memberchose")
    public String memberchose() {
        return "login/memberchose";
    }

    @GetMapping("/memberterms")
    public String memberterms() {
        return "login/memberterms";
    }


    @GetMapping("/finishjoin")
    public String finishjoin() {
        return "login/finishjoin";
    }

}