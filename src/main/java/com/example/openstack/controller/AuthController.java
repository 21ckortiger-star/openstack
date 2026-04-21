package com.example.openstack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.openstack.domain.Member;
import com.example.openstack.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name,
            RedirectAttributes redirectAttributes) {
        try {
            authService.signup(username, password, name);
            redirectAttributes.addFlashAttribute("successMessage", "회원가입 완료! 로그인해 주세요.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/signup";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Member loginMember = authService.login(username, password);
        if (loginMember != null) {
            session.setAttribute("LOGIN_USER", loginMember.getUsername());
            session.setAttribute("LOGIN_NAME", loginMember.getName());
            redirectAttributes.addFlashAttribute("successMessage", "로그인 성공");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "로그인 실패");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("logoutMessage", "로그아웃되었습니다.");
        return "redirect:/";
    }
}
