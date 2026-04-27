package com.example.job_search.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/profile")
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request

                        .requestMatchers("/auth/register", "/auth/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        //Вакансии читают все
                        .requestMatchers(HttpMethod.GET, "/vacancies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vacancies/**").permitAll()

                        // Резюме только после авторизации
                        .requestMatchers(HttpMethod.GET, "/resumes").authenticated()
                        .requestMatchers(HttpMethod.GET, "/resumes/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/resumes").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/resumes/create").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasRole("APPLICANT")

                        // Employer может создавать вакансии
                        .requestMatchers(HttpMethod.POST, "/vacancies/create").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/vacancies/create").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/vacancies").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasRole("EMPLOYER")

                        //profile смотрят только авториз
                        .requestMatchers(HttpMethod.POST, "/profile/update").authenticated()
                        .requestMatchers(HttpMethod.POST, "/profile/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/profile/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/profile").authenticated()

                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
