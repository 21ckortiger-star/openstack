package com.example.openstack.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        String loginName = (String) session.getAttribute("LOGIN_NAME");
        String loginUser = (String) session.getAttribute("LOGIN_USER");
        model.addAttribute("isLoggedIn", loginUser != null);
        model.addAttribute("loginName", loginName);
        return "index";
    }
}
