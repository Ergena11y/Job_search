package com.example.job_search.controller;

import com.example.job_search.dto.ResumeDto;
import com.example.job_search.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("create")
    public String createF(Model model){
        model.addAttribute("resumeDto", new ResumeDto());
        return "resumes/create_resumes";
    }

    @PostMapping("create")
    public String create(Model model, ResumeDto resumeDto, BindingResult br){
        if (br.hasErrors()){
            model.addAttribute("resumeDto", resumeDto);
            return "resumes/create_resumes";
        }
        resumeService.createResumes(resumeDto);
        return "redirect:/resumes";
    }



    @GetMapping("edit/{id}")
    public  String editF(@PathVariable int id, Model model){
        model.addAttribute("resume", resumeService.getById(id));
        model.addAttribute("id", id);
        return "resume/edit";
    }


    @PostMapping("edit/{id}")
    public  String edit(@PathVariable int id, @Valid ResumeDto resumeDto, Model model ,BindingResult br){
        if (br.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            return "resume/edit";
        }
        resumeService.updateResumes(id, resumeDto);
        return "redirect:/resumes";
    }

    @DeleteMapping("delete/{id}")
    public  String delete(@PathVariable int id) {
        resumeService.deleteResumes(id);
        return  "redirect:/resumes";
    }

}
