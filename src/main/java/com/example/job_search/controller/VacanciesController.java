package com.example.job_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vacancies")
public class VacanciesController {

    @GetMapping
    public String vacancies(Model model){
        return  "vacancies";
    }
}
