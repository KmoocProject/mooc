package net.fullstack7.mooc.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.mapper.MemberMapper;
import net.fullstack7.mooc.repository.MemberRepository;
import net.fullstack7.mooc.service.MemberServiceIf;
import net.fullstack7.mooc.util.CookieUtil;
import net.fullstack7.mooc.util.JSFunc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;

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

    //로그인
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
            session.setAttribute("memberDTO", memberDTO);

            String redirectURL = (String) session.getAttribute("redirectAfterLogin");
            if (redirectURL != null) {
                session.removeAttribute("redirectAfterLogin");
                return "redirect:" + redirectURL;
            }
                return "redirect:/main/main";
            } else {
                model.addAttribute("errors", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return "login/login";
            }
    }
    
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        return "redirect:/main/main";
    }

    //약관동의
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

    //회원등록
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

    //아이디 중복체크
    @PostMapping("/memberIdCheck")
    @ResponseBody
    public String checkMemberId(@RequestParam String memberId){
        boolean available = memberService.memberIdCheck(memberId);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("available", available);
        return jsonResponse.toString();
    }

    //이메일 중복체크
    @PostMapping("/emailCheck")
    @ResponseBody
    public String checkEmail(@RequestParam String email) {
        boolean available = memberService.emailCheck(email); // 이메일 중복 체크 로직
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("available", available);
        return jsonResponse.toString();
    }

    //회원 등록
    @PostMapping("/regist")
    public String regist(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        String email = memberDTO.getEmail();
        boolean isEmailAvailable = memberService.emailCheck(email);
        String memberId = memberDTO.getMemberId();
        boolean isMemberIdAvailable = memberService.memberIdCheck(memberId);

        if (!isEmailAvailable || !isMemberIdAvailable) {
            redirectAttributes.addFlashAttribute("errors", "중복된 내용은 등록이 불가합니다.");
            return "redirect:/login/regist";  // 중복된 이메일이 있으면 회원가입 페이지로 리다이렉트
        }

        if(bindingResult.hasErrors()) {
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

    //회원선택
    @GetMapping("/memberchose")
    public String memberchose() {
        return "login/memberchose";
    }

    @GetMapping("/finishjoin")
    public String finishjoin() {
        return "login/finishjoin";
    }

    //아이디 찾기
    @GetMapping("/findId")
    public String findId(Model model, HttpServletRequest request) {
        return "login/findId";
    }

    @PostMapping("/findId")
    public String findIdPost(MemberDTO memberDTO,HttpServletResponse response) {
        String findId = memberService.findId(memberDTO);
        if(!"fail".equals(findId)) {
            try{
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('" + memberDTO.getEmail() + "님의 아이디는 [" + findId + "] 입니다.' );</script>");
                w.write("<script>location.href='/login/login';</script>");
                w.flush();
                w.close();
                return null;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        } else {
            try{
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('입력한 회원정보가 없습니다. 다시 확인해주세요.');</script>");
                w.write("<script>location.href='/login/findId';</script>");
                w.flush();
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    //비밀번호 찾기
    @GetMapping("/findPwd")
    public String findPwd() {
        return "login/findPwd";
    }
    @PostMapping("/findPwd")
    public String findPwdPost(@RequestParam String memberId, HttpSession session, Model model) {
        return "redirect:/login/login";
    }
}