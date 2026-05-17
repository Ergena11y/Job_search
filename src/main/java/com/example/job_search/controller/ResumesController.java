package com.example.job_search.controller;

import com.example.job_search.dto.ResumeDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.repository.CategoryRepository;
import com.example.job_search.service.ResumeService;
import com.example.job_search.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        Page<@Valid ResumeDto> resumePage = resumeService.getAllResumes(page, size);
        model.addAttribute("resumes", resumePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resumePage.getTotalPages());
        model.addAttribute("totalItems", resumePage.getTotalElements());
        addCurrentUser(principal, model);

        return "resumes/resumes";
    }

    @GetMapping("create")
    public String createForm(Model model, Principal principal) {
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categoryRepository.findAll());
        addCurrentUser(principal, model);
        return "resumes/create_resumes";
    }

    @PostMapping("create")
    public String create(Model model, @Valid ResumeDto resumeDto,
                         BindingResult br, Principal principal) throws UserNotFoundException {
        if (br.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            model.addAttribute("bindingRes", br);
            model.addAttribute("categories", categoryRepository.findAll());
            addCurrentUser(principal, model);
            return "resumes/create_resumes";
        }
        int userId = userService.getUserIdByEmail(principal.getName());
        resumeDto.setApplicantId((long) userId);
        resumeService.createResumes(resumeDto);
        return "redirect:/resumes";
    }

    @GetMapping("edit/{id}")
    public String editForm(@PathVariable int id, Model model, Principal principal) {
        ResumeDto resume = resumeService.getById(id);
        checkOwnership(resume.getApplicantId(), principal);
        model.addAttribute("resume", resumeService.getById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("id", id);
        addCurrentUser(principal, model);
        return "resumes/edit_resume";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable int id, @Valid ResumeDto resumeDto,
                       BindingResult br, Model model, Principal principal) {
        ResumeDto existing = resumeService.getById(id);
        checkOwnership(existing.getApplicantId(), principal);


        if (br.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            model.addAttribute("resume", resumeService.getById(id));
            model.addAttribute("bindingRes", br);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("id", id);
            addCurrentUser(principal, model);
            return "resumes/edit_resume";
        }
        resumeService.updateResumes(id, resumeDto);
        return "redirect:/resumes";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id, Principal principal) {
        ResumeDto resume = resumeService.getById(id);
        checkOwnership(resume.getApplicantId(), principal);

        resumeService.deleteResumes(id);
        return "redirect:/resumes";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model, Principal principal) {
        model.addAttribute("resume", resumeService.getById(id));
        addCurrentUser(principal, model);
        return "resumes/resumes_info";
    }


    private  void addCurrentUser(Principal principal, Model model){
        if (principal != null){
            try {
                model.addAttribute("user", userService.getByEmail(principal.getName()));
            } catch (UserNotFoundException e) {
                model.addAttribute("user", null);
            }
        }   else {
            model.addAttribute("user", null);
        }
    }


    private void checkOwnership(Long applicantId, Principal principal) throws  UserNotFoundException{
        int currentUserId = userService.getUserIdByEmail(principal.getName());
        if (applicantId == null || applicantId.intValue() != currentUserId ){
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN, "У вас нет прав для редактирования этого резюме");
        }
    }


}
