package com.example.job_search.controller;


import com.example.job_search.dto.RegisterDto;
import com.example.job_search.dto.RegisterEmployerDto;
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
        model.addAttribute("employerDto", new RegisterEmployerDto());
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "auth/login";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String accountType,
            @Valid @ModelAttribute("registerDto") RegisterDto registerDto,
            BindingResult applicantBinding,
            @Valid @ModelAttribute("employerDto") RegisterEmployerDto employerDto,
            BindingResult employerBinding,
            Model model,
            HttpServletRequest request) {

        boolean isEmployer = "EMPLOYER".equals(accountType);

        if (isEmployer && employerBinding.hasErrors()) {
            model.addAttribute("employerDto", employerDto);
            model.addAttribute("bindingRes", employerBinding);
            return "auth/register";
        }
        if (!isEmployer && applicantBinding.hasErrors()) {
            model.addAttribute("registerDto", registerDto);
            model.addAttribute("bindingRes", applicantBinding);
            return "auth/register";
        }

        User user = new User();
        user.setEnabled(true);
        user.setAccountType(accountType);

        if (isEmployer) {
            user.setName(employerDto.getName());
            user.setSurname("");
            user.setAge(0);
            user.setEmail(employerDto.getEmail());
            user.setPassword(employerDto.getPassword());
            user.setPhoneNumber(employerDto.getPhoneNumber());
        } else {
            user.setName(registerDto.getName());
            user.setSurname(registerDto.getSurname());
            user.setAge(registerDto.getAge());
            user.setEmail(registerDto.getEmail());
            user.setPassword(registerDto.getPassword());
            user.setPhoneNumber(registerDto.getPhoneNumber());
        }

        try {
            userService.register(user);
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
            return "auth/register";
        }

        try {
            String password = isEmployer ? employerDto.getPassword() : registerDto.getPassword();
            String email    = isEmployer ? employerDto.getEmail()    : registerDto.getEmail();
            request.login(email, password);
        } catch (ServletException e) {
            return "redirect:/auth/login";
        }

        return isEmployer ? "redirect:/resumes" : "redirect:/vacancies";
    }
}
