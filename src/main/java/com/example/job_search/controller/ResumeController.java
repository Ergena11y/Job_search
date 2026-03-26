package com.example.job_search.controller;



import com.example.job_search.dto.ResumeDto;

import com.example.job_search.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public List<ResumeDto> getAll(){
        return resumeService.getAllResumes();
    }

    @GetMapping("applicant/{applicantId}")
    public List<ResumeDto> getByApplicant(@PathVariable int applicantId) {
        return resumeService.getByApplicant(applicantId);
    }


    @PostMapping
    public void create(@Valid @RequestBody ResumeDto resume){
        resumeService.createResumes(resume);
    }

    @PutMapping("{id}")
    public void update ( @PathVariable int id, @Valid @RequestBody ResumeDto resume){
        resumeService.updateResumes(id, resume);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        resumeService.deleteResumes(id);

    }
    @GetMapping("category/{categoryId}")
    public List<ResumeDto> getByCategory(@PathVariable int categoryId) {
        return resumeService.getByCategory(categoryId);
    }
}
