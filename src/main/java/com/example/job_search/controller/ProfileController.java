package com.example.job_search.controller;

import com.example.job_search.dto.ResumeDto;
import com.example.job_search.dto.UpdateProfileDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.exception.UserProfileNotFoundException;
import com.example.job_search.service.ResumeService;
import com.example.job_search.service.VacancyService;
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

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile( @RequestParam int userId,
                           @RequestParam (defaultValue = "0") int vacancyPage,
                           @RequestParam(defaultValue = "0") int resumePage,
                           @RequestParam(defaultValue = "5") int size,
                           Model model) throws UserNotFoundException {
        UserDto user = userService.getById(userId);
        model.addAttribute("user", user);

        //Employer -> его вакансии
        if ("EMPLOYER".equals(user.getAccountType())){
            Page<VacanciesDto> vacancies = vacancyService.getByAuthor(userId, vacancyPage, size);
            model.addAttribute("vacancies", vacancies.getContent());
            model.addAttribute("currentVacancyPage", vacancyPage);
            model.addAttribute("totalVacancyPages", vacancies.getTotalPages());
        }else { // Applicant
            Page<ResumeDto> resumes = resumeService.getByApplicant(userId, resumePage, size);
            model.addAttribute("resumes",  resumes.getContent());
            model.addAttribute("currentResumesPage",  resumePage);
            model.addAttribute("totalResPage",  resumes.getTotalPages());
        }
        return "profile/profile";
    }

    @GetMapping("edit")
    public  String ProfileEdit(@RequestParam int userId,  Model model) throws UserNotFoundException {
        UserDto user = userService.getById(userId);
        model.addAttribute("user", user);
        model.addAttribute("edit", user);
        model.addAttribute("updateProfileDto", new UpdateProfileDto());
        return "profile/profile-edit";
    }

    @PostMapping("/update")
    public String profileUpdate(Model model, @RequestParam int userId, UpdateProfileDto dto, @RequestParam(required = false)MultipartFile avatar) throws UserProfileNotFoundException {
        userService.updateUserProfile(userId, dto, avatar);
        return "redirect:/profile?userId=" + userId;
    }
}
