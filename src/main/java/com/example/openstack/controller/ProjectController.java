package com.example.openstack.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProjectController {

    @GetMapping("/project/1")
    public String project1() {
        return "project1";
    }

    @GetMapping("/project/2")
    public String project2() {
        return "project2";
    }

    @GetMapping("/project/3")
    public String project3() {
        return "project3";
    }
}
