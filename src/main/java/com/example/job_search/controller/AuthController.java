package com.example.job_search.controller;


import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserDataCreateException;
import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model,
                           HttpServletRequest request) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("roles", List.of("APPLICANT", "EMPLOYER"));
            return "auth/register";
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(true);
        user.setAccountType(userDto.getAccountType() != null ? userDto.getAccountType() : "APPLICANT");

        userService.register(user);

        try {
            request.login(userDto.getEmail(), userDto.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/profile";
    }
}
