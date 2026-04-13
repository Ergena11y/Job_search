package com.example.job_search.controller;

import com.example.job_search.dto.UpdateProfileDto;
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
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("edit")
    public  String ProfileEdit(@RequestParam int userId,  Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.getById(userId));
        model.addAttribute("updateProfileDto", new UpdateProfileDto());
        return "profile/profile-edit";
    }

    @PostMapping("/update")
    public String profileUpdate(Model model, @RequestParam int userId, UpdateProfileDto dto, @RequestParam(required = false)MultipartFile avatar) throws UserNotFoundException {
        userService.updateUserProfile(userId, dto, avatar);
        return "redirect:/profile?userId=" + userId;
    }
}
