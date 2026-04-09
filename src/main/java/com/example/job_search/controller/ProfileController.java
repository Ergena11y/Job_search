package com.example.job_search.controller;

import com.example.job_search.exception.UserNotFoundException;
import org.springframework.ui.Model;
import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String profile(@RequestParam int userId, Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.getById(userId));
        return "profile/profile";
    }

    @PostMapping("profile-edit")
    public  String ProfileEdit(@RequestParam int userId,  Model model) throws UserNotFoundException {
        model.addAttribute("edit", userService.getById(userId));
        return "profile/profile-edit";
    }
}
