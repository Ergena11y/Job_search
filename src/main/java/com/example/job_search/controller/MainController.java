package com.example.job_search.controller;

import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.service.UserService;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
