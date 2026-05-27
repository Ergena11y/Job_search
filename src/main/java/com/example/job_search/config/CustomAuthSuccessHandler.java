package com.example.job_search.config;

import com.example.job_search.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;
import java.util.Locale;

@Slf4j
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
        String targetUrl = isEmployer ? "/resumes" : "/vacancies";

        clearAuthenticationAttributes(request);

        if (response.isCommitted()){
            log.warn("Ответ уже отправлен, редирект на {} невозможен", targetUrl);
            return;
        }

        response.sendRedirect(isEmployer ? "/resumes" : "/vacancies");
    }

    private   void  clearAuthenticationAttributes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}