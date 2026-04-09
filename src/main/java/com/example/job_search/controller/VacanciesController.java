package com.example.job_search.controller;

import com.example.job_search.service.UserService;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController {

    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping
    public String vacancies(Model model){
        return  "vacancies";
    }
}
