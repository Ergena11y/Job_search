package com.example.job_search.controller;

import com.example.job_search.common.ResumeFormParser;
import com.example.job_search.dto.EducationDto;
import com.example.job_search.dto.ResumeDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.dto.WorkExperienceDto;
import com.example.job_search.exception.ForbiddenException;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.repository.CategoryRepository;
import com.example.job_search.repository.RespondedApplicantsRepository;
import com.example.job_search.service.ResumeService;
import com.example.job_search.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumesController {

    private final ResumeService resumeService;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final ResumeFormParser resumeFormParser;
    private final RespondedApplicantsRepository respondedApplicantsRepository;

    @GetMapping
    public String resumes(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(required = false) Integer categoryId,
                          Principal principal,
                          Model model) throws UserNotFoundException {
        addCurrentUser(principal, model);

        UserDto currentUser = userService.getByEmail(principal.getName());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("selectedCategory", categoryId);

        if ("EMPLOYER".equals(currentUser.getAccountType())) {
            Page<@Valid ResumeDto> resumePage = categoryId != null
                    ? resumeService.getByCategory(categoryId, page, size)
                    : resumeService.getAllResumes(page, size);
            model.addAttribute("resumes", resumePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", resumePage.getTotalPages());
            model.addAttribute("totalItems", resumePage.getTotalElements());
        } else {
            int userId = userService.getUserIdByEmail(principal.getName());
            Page<@Valid ResumeDto> resumePage = resumeService.getByApplicant(userId, page, size);
            model.addAttribute("resumes", resumePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", resumePage.getTotalPages());
            model.addAttribute("totalItems", resumePage.getTotalElements());
        }

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
                         BindingResult br, Principal principal,
                         HttpServletRequest request) throws UserNotFoundException {
        if (br.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            model.addAttribute("bindingRes", br);
            model.addAttribute("categories", categoryRepository.findAll());
            addCurrentUser(principal, model);
            return "resumes/create_resumes";
        }
        int userId = userService.getUserIdByEmail(principal.getName());
        resumeDto.setApplicantId((long) userId);

        List<WorkExperienceDto> workList = resumeFormParser.parseWorkExperience(request);
        List<EducationDto> eduList = resumeFormParser.parseEducation(request);

        resumeService.createResumes(resumeDto, workList, eduList);
        return "redirect:/profile";
    }

    @GetMapping("edit/{id}")
    public String editForm(@PathVariable int id, Model model, Principal principal) {
        ResumeDto resume = resumeService.getById(id);
        checkOwnership(resume.getApplicantId(), principal);
        model.addAttribute("resume", resume);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("id", id);
        addCurrentUser(principal, model);
        return "resumes/edit_resume";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable int id, @Valid ResumeDto resumeDto,
                       BindingResult br, Model model, Principal principal,
                       HttpServletRequest request) {
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

        List<WorkExperienceDto> workList = resumeFormParser.parseWorkExperience(request);
        List<EducationDto> eduList = resumeFormParser.parseEducation(request);
        resumeService.updateResumes(id, resumeDto, workList, eduList);
        return "redirect:/profile";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable int id, Principal principal) {
        ResumeDto resume = resumeService.getById(id);
        checkOwnership(resume.getApplicantId(), principal);
        resumeService.deleteResumes(id);
        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model, Principal principal) {
        ResumeDto resume = resumeService.getById(id);
        addCurrentUser(principal, model);

        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            int currentUserId = userService.getUserIdByEmail(principal.getName());
            UserDto currentUser = userService.getByEmail(principal.getName());

            boolean isOwner = resume.getApplicantId() != null
                    && resume.getApplicantId().intValue() == currentUserId;
            boolean isEmployer = "EMPLOYER".equals(currentUser.getAccountType());

            if (!isOwner && !isEmployer) {
                throw new ForbiddenException();
            }

            if (isEmployer) {
                respondedApplicantsRepository
                        .findByResumeApplicantId(resume.getApplicantId().intValue())
                        .stream()
                        .filter(ra -> ra.getVacancy().getAuthor().getId() == currentUserId)
                        .findFirst()
                        .ifPresent(ra -> model.addAttribute("respondedId", ra.getId()));
            }

            if (isOwner) {
                List<RespondedApplicants> myResponds =
                        respondedApplicantsRepository.findByResumeApplicantId(currentUserId);
                model.addAttribute("myResponds", myResponds);
            }

        } catch (UserNotFoundException e) {
            throw new ForbiddenException();
        }

        model.addAttribute("resume", resume);
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


    private void checkOwnership(Long applicantId, Principal principal){
        try {
            int currentUserId = userService.getUserIdByEmail(principal.getName());
            if (applicantId == null || applicantId.intValue() != currentUserId ){
                throw new ForbiddenException();
            }
        }catch (UserNotFoundException e){
            throw  new ForbiddenException();
        }
    }


}
