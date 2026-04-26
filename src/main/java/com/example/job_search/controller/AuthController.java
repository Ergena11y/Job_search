package com.example.job_search.controller;


import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserDataCreateException;
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
                           HttpServletRequest request) throws UserDataCreateException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("roles", List.of("APPLICANT", "EMPLOYER"));
            return "auth/register";
        }

        log.info("Начало регистрации для email: {}", userDto.getEmail());

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(true);
        user.setAccountType(userDto.getAccountType() != null ? userDto.getAccountType() : "APPLICANT");


        log.debug("User объект перед сохранением: {}", user);

        try{
            userService.register(user);
            log.info("Пользователь успешно зарегистрирован: {}", userDto.getEmail());
        }catch (Exception e){
            log.error("Ошибка при регистрации: {}", e.getMessage(), e);
            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
            return "auth/register";
        }


        try {
            request.login(userDto.getEmail(), userDto.getPassword());
            log.info("Автологин успешен");
        } catch (ServletException e) {
            e.printStackTrace();
            log.error("Ошибка автологина: {}", e.getMessage(), e);
        }

        return "redirect:/profile";
    }
}
