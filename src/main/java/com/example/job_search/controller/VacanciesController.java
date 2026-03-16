package com.example.job_search.controller;


import com.example.job_search.model.Vacancies;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController {

    @GetMapping
    public List<Vacancies>getAll(){
        return List.of();
    }

    @PostMapping
    public void create(@RequestBody Vacancies vacancy){}


    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody  Vacancies vacancy){}


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){}


    @GetMapping("category/{categoryId}")
    public List<Vacancies> getByCategory(@PathVariable int categoryId){
        return List.of();
    }

}
