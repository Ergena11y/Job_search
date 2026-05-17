package com.example.job_search.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleBasedSuccessHandler  implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected  void handle(HttpServletRequest request,
                           HttpServletResponse response,
                           Authentication authentication) throws  IOException{
        String targetUtl = determineTargetUrl(authentication);
        if(response.isCommitted()){
            log.info("Ответ уже отправлен, перенаправление невозможно {}.", targetUtl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUtl);
    }

    protected  String determineTargetUrl(Authentication authentication){
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_APPLICANT", "/vacancies");
        roleTargetUrlMap.put("ROLE_EMPLOYER", "/resumes");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (roleTargetUrlMap.containsKey(role)){
                return roleTargetUrlMap.get(role);
            }
        }
        throw new IllegalStateException("Неизвестная роль пользователя");
    }

    protected  void  clearAuthenticationAttributes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
