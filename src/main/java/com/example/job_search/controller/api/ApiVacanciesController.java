package com.example.job_search.controller.api;


import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vacancies")
@RequiredArgsConstructor
public class ApiVacanciesController {

    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<Page<VacanciesDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy) {
        return ResponseEntity.ok(vacancyService.getAllVacancies(page, size, sortBy));
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<Page<VacanciesDto>> getByCategory(
            @PathVariable int categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(vacancyService.getByCategory(categoryId, page, size));
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
