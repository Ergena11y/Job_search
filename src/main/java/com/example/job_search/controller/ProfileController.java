package com.example.job_search.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class ProfileController {
    @GetMapping
    public String profile(Model model){
        return "profile";
    }

    @GetMapping("profile-edit")
    public  String ProfileEdit(Model model){
        return "profile-edit";
    }
}
