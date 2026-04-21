package com.example.openstack.controller;

import com.example.openstack.domain.Member;
import com.example.openstack.repository.AuthRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final AuthRepository authRepository;

    public HomeController(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            String loginName = authRepository.findByUsername(username)
                    .map(Member::getName)
                    .orElse(username);
            
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("loginName", loginName);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "index";
    }
}
