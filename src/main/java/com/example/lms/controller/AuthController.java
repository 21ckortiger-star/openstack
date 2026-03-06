package com.example.lms.controller;

import com.example.lms.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam String name
    ) {
        authService.validateSignup(username, password, name);
        return "redirect:/login";
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
            RedirectAttributes redirectAttributes
    ) {
        if (authService.login(username, password)) {
            session.setAttribute("LOGIN_USER", username);
            redirectAttributes.addFlashAttribute("successMessage", "로그인 성공");
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "로그인 실패");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("logoutMessage", "로그아웃되었습니다.");
        return "redirect:/login";
    }
}