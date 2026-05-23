package com.example.job_search.controller;

import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.service.UserService;
import com.example.job_search.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping("/")
    public String  index(Principal principal, Model model){
        if (principal != null){
            try {
                model.addAttribute("user", userService.getByEmail(principal.getName()));
            }catch (UserNotFoundException e){
                model.addAttribute("user", null);
            }
        }else {
            model.addAttribute("user", null);
        }
        
        return "index";
    }

    @GetMapping("layout")
    public  String layout(Model model) {
        return "layout";
    }
}
