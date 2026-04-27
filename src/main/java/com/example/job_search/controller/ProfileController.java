package com.example.job_search.controller;

import com.example.job_search.dto.ResumeDto;
import com.example.job_search.dto.UpdateProfileDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.exception.UserProfileNotFoundException;
import com.example.job_search.service.ResumeService;
import com.example.job_search.service.VacancyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile( Principal principal,
                           @RequestParam (defaultValue = "0") int vacancyPage,
                           @RequestParam(defaultValue = "0") int resumePage,
                           Model model) throws UserNotFoundException {
        String email = principal.getName();
        UserDto user = userService.getByEmail(email);
        int userId = userService.getUserIdByEmail(email);

        model.addAttribute("user", user);

        //Employer -> его вакансии
        if ("EMPLOYER".equals(user.getAccountType())){
            var vacancies = vacancyService.getByAuthor(userId, vacancyPage, 5);
            model.addAttribute("vacancies", vacancies.getContent());
            model.addAttribute("currentVacancyPage", vacancyPage);
            model.addAttribute("totalVacancyPages", vacancies.getTotalPages());

            var allResumes = resumeService.getAllResumes(resumePage, 5);
            model.addAttribute("resumes", allResumes.getContent());
            model.addAttribute("currentResumesPage", resumePage);
            model.addAttribute("totalResPage", allResumes.getTotalPages());
        }else { // Applicant
            var resumes = resumeService.getByApplicant(userId, resumePage, 5);
            model.addAttribute("resumes",  resumes.getContent());
            model.addAttribute("currentResumesPage",  resumePage);
            model.addAttribute("totalResPage",  resumes.getTotalPages());
        }
        return "profile/profile";
    }

    @GetMapping("edit")
    public  String ProfileEdit(Principal principal,  Model model) throws UserNotFoundException {

        UserDto user = userService.getByEmail(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("edit", user);
        model.addAttribute("updateProfileDto", new UpdateProfileDto());
        return "profile/profile-edit";
    }

    @PostMapping("/update")
    public String profileUpdate(Principal principal, @Valid UpdateProfileDto dto) throws UserProfileNotFoundException, UserNotFoundException {

        int userId = userService.getUserIdByEmail(principal.getName());
        userService.updateUserProfile(userId, dto, dto.getAvatar());
        return "redirect:/profile";
    }
}
