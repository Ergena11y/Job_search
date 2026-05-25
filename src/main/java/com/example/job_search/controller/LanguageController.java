package com.example.job_search.controller;

import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {
    private final UserService userService;
    private final LocaleResolver localeResolver;

    @GetMapping("/change")
    public String changeLanguage(
            @RequestParam(value = "lang", required = false) String lang,
            Authentication authentication,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (lang != null) {
            localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));

            if (authentication != null && authentication.isAuthenticated()) {
                try {
                    int userId = userService.getUserIdByEmail(authentication.getName());
                    userService.updateUserLanguage(userId, lang);
                    log.info("Язык пользователя {} установлен на: {}", userId, lang);
                } catch (Exception e) {
                    log.error("Ошибка при сохранении языка: {}", e.getMessage());
                }
            }
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
