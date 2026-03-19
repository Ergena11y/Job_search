package com.example.job_search.controller;


import com.example.job_search.model.Vacancies;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController {

    private final VacancyService vacancyService;

    @GetMapping
    public List<Vacancies>getAll(){
        return vacancyService.getAllVacancies();
    }

    @GetMapping("category/{categoryId}")
    public List<Vacancies> getByCategory(@PathVariable int categoryId){
        return vacancyService.getByCategory(categoryId);
    }

    @GetMapping("responded/{applicantId}")
    public List<Vacancies> getRespondedByUser(@PathVariable int applicantId) {
        return vacancyService.getRespondedByUser(applicantId);
    }

    @PostMapping
    public void create(@RequestBody Vacancies vacancy){
        vacancyService.createVacancy(vacancy);
    }


    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody  Vacancies vacancy){
        vacancyService.updateVacancy(id, vacancy);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        vacancyService.deleteVacancy(id);
    }


}
