package com.example.job_search.controller;

import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.service.UserService;
import com.example.job_search.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController {

    private final VacancyService vacancyService;

    @GetMapping
    public String vacancies(Model model){
        model.addAttribute("vacancies", vacancyService.getAllVacancies());
        return  "vacancies/vacancies";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("vacancyDto", new VacanciesDto());
        return "vacancies/create_vacancies";
    }

    @PostMapping("/create")
    public String create(@Valid VacanciesDto dto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("vacancyDto", dto);
            return "vacancies/create_vacancies";
        }
        vacancyService.createVacancy(dto);
        return "redirect:/vacancies";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("vacancyDto", vacancyService.getById(id));
        model.addAttribute("id", id);
        return "vacancies/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @Valid VacanciesDto dto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("vacancyDto", dto);
            return "vacancies/edit";
        }
        vacancyService.updateVacancy(id, dto);
        return "redirect:/vacancies";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        vacancyService.deleteVacancy(id);
        return "redirect:/vacancies";
    }
}
