package com.example.job_search.controller;

import com.example.job_search.dto.ResumeDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.service.ResumeService;
import com.example.job_search.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumesController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping
    public String resumes(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Principal principal,
                          Model model) throws UserNotFoundException {

        int userId = userService.getUserIdByEmail(principal.getName());

        Page<ResumeDto> resumePage = resumeService.getAllResumes(page, size);
        model.addAttribute("resumes", resumePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resumePage.getTotalPages());
        model.addAttribute("totalItems", resumePage.getTotalElements());
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

    @GetMapping("/{id}")
    public  String getById(@PathVariable int id, Model model){
        model.addAttribute("resume", resumeService.getById(id));
        return "resumes/detail";
    }

}
