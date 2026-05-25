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

    private  final  RoleBasedSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler(successHandler)
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

                        //Компании - только соискатели
                        .requestMatchers(HttpMethod.GET, "/companies").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.GET, "/companies/**").hasRole("APPLICANT")

                        // Резюме т
                        .requestMatchers(HttpMethod.GET, "/resumes").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/resumes/create").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.GET, "/resumes/edit/**").hasRole("APPLICANT")


                        .requestMatchers(HttpMethod.POST, "/resumes/create").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/resumes/edit/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/resumes/delete/**").hasRole("APPLICANT")

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

                        .requestMatchers(HttpMethod.GET, "/vacancies/*/responses").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/responses/approve/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/responses/reject/**").hasRole("EMPLOYER")

                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/static/**").permitAll()

                        .requestMatchers("/auth/forgot-password", "/auth/reset-password").permitAll()

                        .requestMatchers("/chat/**").authenticated()
                        .requestMatchers("/ws/**").authenticated()

                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
