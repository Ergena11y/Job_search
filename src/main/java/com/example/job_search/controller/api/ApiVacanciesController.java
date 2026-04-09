package com.example.job_search.controller.api;


import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vacancies")
@RequiredArgsConstructor
public class ApiVacanciesController {

    private final VacancyService vacancyService;

    @GetMapping
    public List<VacanciesDto>getAll(){
        return vacancyService.getAllVacancies();
    }

    @GetMapping("category/{categoryId}")
    public List<VacanciesDto> getByCategory(@PathVariable int categoryId){
        return vacancyService.getByCategory(categoryId);
    }

    @GetMapping("responded/{applicantId}")
    public List<VacanciesDto> getRespondedByUser(@PathVariable int applicantId) {
        return vacancyService.getRespondedByUser(applicantId);
    }

    @PostMapping
    public void create( @Valid @RequestBody VacanciesDto vacancy){
        vacancyService.createVacancy(vacancy);
    }


    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody  @Valid VacanciesDto vacancy){
        vacancyService.updateVacancy(id, vacancy);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        vacancyService.deleteVacancy(id);
    }


}
