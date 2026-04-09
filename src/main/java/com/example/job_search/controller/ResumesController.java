package com.example.job_search.controller;

import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumesController {
    private final ResumeService resumeService;
    @GetMapping
    public String resumes(Model model) {
        model.addAttribute("resumes", resumeService.getAllResumes());
        return "resumes/resumes";
    }
}
