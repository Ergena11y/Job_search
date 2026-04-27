package com.example.job_search.controller;

import com.example.job_search.common.UrlBuilder;
import com.example.job_search.service.PasswordResetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    // Страница запроса сброса
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "auth/forgot-password";
    }

    // Обработка запроса письма
    @PostMapping("/forgot-password")
    public String sendResetLink(@RequestParam String email,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes) {
        try {
            String siteUrl = UrlBuilder.getSiteUrl(request);
            passwordResetService.sendResetEmail(email, siteUrl);
            redirectAttributes.addFlashAttribute("message",
                    "Письмо с инструкцией отправлено на " + email);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Пользователь с таким email не найден");
        }
        return "redirect:/auth/forgot-password";
    }

    // Страница ввода нового пароля
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "auth/reset-password";
    }

    // Обработка нового пароля
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password,
                                RedirectAttributes redirectAttributes) {
        try {
            passwordResetService.resetPassword(token, password);
            redirectAttributes.addFlashAttribute("message", "Пароль успешно изменён");
            return "redirect:/auth/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Неверный или устаревший токен");
            return "redirect:/auth/forgot-password";
        }
    }
}