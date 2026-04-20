package com.example.job_search.controller;


import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private  final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("roles", List.of("APPLICANT", "EMPLOYER"));
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "auth/login";
    }

    @PostMapping("/register")
    public String registerUser(User user, @RequestParam String accountType) {
        user.setAccountType(accountType);
        userService.register(user);
        return "redirect:auth/login";
    }
}
