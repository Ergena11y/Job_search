package com.example.job_search.controller.api;



import com.example.job_search.dto.ResumeDto;

import com.example.job_search.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/resumes")
@RequiredArgsConstructor
public class ApiResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<Page<ResumeDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(resumeService.getAllResumes(page, size));
    }

    @GetMapping("applicant/{applicantId}")
    public ResponseEntity<Page<ResumeDto>> getByApplicant(
            @PathVariable int applicantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(resumeService.getByApplicant(applicantId, page, size));
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
    public ResponseEntity<Page<ResumeDto>> getByCategory(
            @PathVariable int categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(resumeService.getByCategory(categoryId, page, size));
    }
}
