package com.example.job_search.controller;


import com.example.job_search.model.Resumes;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public List<Resumes> getAll(){
        return resumeService.getAllResumes();
    }

    @GetMapping("applicant/{applicantId}")
    public List<Resumes> getByApplicant(@PathVariable int applicantId) {
        return resumeService.getByApplicant(applicantId);
    }


    @PostMapping
    public void create(@RequestBody Resumes resume){
        resumeService.createResumes(resume);
    }

    @PutMapping("{id}")
    public void update (@PathVariable int id,@RequestBody Resumes resume){
        resumeService.updateResumes(id, resume);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        resumeService.deleteResumes(id);

    }
    @GetMapping("category/{categoryId}")
    public List<Resumes> getByCategory(@PathVariable int categoryId) {
        return resumeService.getByCategory(categoryId);
    }
}
