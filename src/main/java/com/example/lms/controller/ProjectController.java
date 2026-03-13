package com.example.lms.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProjectController {

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("LOGIN_USER") != null;
    }

    @GetMapping("/project/1")
    public String project1(HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 프로젝트를 볼 수 있습니다.");
            return "redirect:/login";
        }
        return "project1";
    }

    @GetMapping("/project/2")
    public String project2(HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 프로젝트를 볼 수 있습니다.");
            return "redirect:/login";
        }
        return "project2";
    }

    @GetMapping("/project/3")
    public String project3(HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 프로젝트를 볼 수 있습니다.");
            return "redirect:/login";
        }
        return "project3";
    }
}
