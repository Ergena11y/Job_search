package com.example.job_search.config;

import com.example.job_search.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String email = authentication.getName();


        userRepository.findByEmail(email).ifPresent(user -> {
            String lang = user.getPreferredLanguage();
            if (lang != null) {
                localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));
            }
        });


        boolean isEmployer = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYER"));

        response.sendRedirect(isEmployer ? "/resumes" : "/vacancies");
    }
}