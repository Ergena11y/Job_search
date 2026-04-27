package com.example.job_search.controller;

import com.example.job_search.dto.ResumeDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String resumes(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Principal principal,
                          Model model) {
        Page<ResumeDto> resumePage = resumeService.getAllResumes(page, size);
        model.addAttribute("resumes", resumePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resumePage.getTotalPages());
        model.addAttribute("totalItems", resumePage.getTotalElements());

        if (principal != null) {
            try {
                model.addAttribute("user", userService.getByEmail(principal.getName()));
            } catch (UserNotFoundException e) {
                model.addAttribute("user", null);
            }
        } else {
            model.addAttribute("user", null);
        }

        return "resumes/resumes";
    }

    @GetMapping("create")
    public String createF(Model model){
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categoryRepository.findAll());
        return "resumes/create_resumes";
    }

    @PostMapping("create")
    public String create(Model model, @Valid ResumeDto resumeDto,
                         BindingResult br, Principal principal) throws UserNotFoundException {
        if (br.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            model.addAttribute("categories", categoryRepository.findAll());
            return "resumes/create_resumes";
        }
        int userId = userService.getUserIdByEmail(principal.getName());
        resumeDto.setApplicantId((long) userId);
        resumeService.createResumes(resumeDto);
        return "redirect:/resumes";
    }



    @GetMapping("edit/{id}")
    public  String editF(@PathVariable int id, Model model){
        model.addAttribute("resume", resumeService.getById(id));
        model.addAttribute("id", id);
        return "resumes/edit_resume";
    }


    @PostMapping("edit/{id}")
    public  String edit(@PathVariable int id, @Valid ResumeDto resumeDto, Model model ,BindingResult br){
        if (br.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            return "resumes/edit_resume";
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
