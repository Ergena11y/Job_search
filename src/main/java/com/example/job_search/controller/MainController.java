package com.example.job_search.controller;

import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.service.UserService;
import com.example.job_search.service.VacancyService;
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
    public String  index(
            Principal principal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            Model model){
        Page<VacanciesDto> vacancies = vacancyService.getAllVacancies(page, size, sortBy);
        model.addAttribute("vacancies", vacancies.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vacancies.getTotalPages());
        model.addAttribute("sortBy", sortBy);

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
