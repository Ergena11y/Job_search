package com.example.job_search.controller;


import com.example.job_search.dto.RegisterDto;
import com.example.job_search.model.User;
import com.example.job_search.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private  final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        model.addAttribute("roles", List.of("APPLICANT", "EMPLOYER"));
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "auth/login";
    }

    @PostMapping("register")
    public String register(@Valid RegisterDto registerDto,
                           BindingResult bindingResult,
                           Model model,
                           HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerDto", registerDto);
            model.addAttribute("bindingRes", bindingResult);
            model.addAttribute("roles", List.of("APPLICANT", "EMPLOYER"));
            return "auth/register";
        }

        log.info("Начало регистрации для email: {}", registerDto.getEmail());

        User user = new User();
        user.setName(registerDto.getName());
        user.setSurname(registerDto.getSurname());
        user.setAge(registerDto.getAge());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setEnabled(true);
        user.setAccountType(registerDto.getAccountType() != null ? registerDto.getAccountType() : "APPLICANT");


        try{
            userService.register(user);
            log.info("Пользователь успешно зарегистрирован: {}", registerDto.getEmail());
        }catch (Exception e){
            log.error("Ошибка при регистрации: {}", e.getMessage(), e);
            model.addAttribute("registerDto", registerDto);
            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
            return "auth/register";
        }


        try {
            request.login(registerDto.getEmail(), registerDto.getPassword());
        } catch (ServletException e) {
            log.error("Ошибка автологина: {}", e.getMessage(), e);
            return "redirect:/auth/login";
        }

        String accountType = registerDto.getAccountType() != null ? registerDto.getAccountType() : "APPLICANT";
        return "EMPLOYER".equals(accountType) ? "redirect:/resumes" : "redirect:/vacancies";
    }
}
