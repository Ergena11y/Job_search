package com.example.job_search.controller;


import com.example.job_search.model.Resumes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {

    @GetMapping
    public List<Resumes> getAll(){
        return List.of();
    }

    @PostMapping
    public void create(@RequestBody Resumes resumes){

    }

    @PutMapping("{id}")
    public void update ( int id,@RequestBody Resumes resume){

    }

    @DeleteMapping("{id}")
    public void delete(int id){

    }
    @GetMapping("category/{categoryId}")
    public List<Resumes> getByCategory(@PathVariable int categoryId) {
        return List.of();
    }
}
