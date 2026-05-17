package com.example.job_search.controller;

import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.model.Resumes;
import com.example.job_search.model.Vacancies;
import com.example.job_search.repository.RespondedApplicantsRepository;
import com.example.job_search.repository.ResumeRepository;
import com.example.job_search.repository.VacancyRepository;
import com.example.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("responses")
@RequiredArgsConstructor
public class ResponseController {

    private final RespondedApplicantsRepository respondedApplicantsRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;

    @PostMapping("apply/{vacancyId}")
    public String apply(@PathVariable int vacancyId,
                        @RequestParam int resumeId,
                        Principal principal,
                        RedirectAttributes redirectAttributes) throws UserNotFoundException {
        int userId = userService.getUserIdByEmail(principal.getName());
        Resumes resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        if (resume.getApplicant() == null || resume.getApplicant().getId() != userId){
            redirectAttributes.addFlashAttribute("error", "Это не ваше резюме");
            return "redirect:/vacancies/" + vacancyId;
        }

        //проверка на дублирующий отклик
        boolean alreadyApplied = respondedApplicantsRepository
                .findByVacancyId(vacancyId)
                .stream()
                .anyMatch(r -> r.getResume().getId() == resumeId);

        if (alreadyApplied){
            redirectAttributes.addFlashAttribute("error","Вы уже откликнулись на эту вакансию");
            return "redirect:/vacancies/" + vacancyId;
        }

        Vacancies vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        RespondedApplicants response = new RespondedApplicants();
        response.setResume(resume);
        response.setVacancy(vacancy);
        response.setConfirmation(false);

        respondedApplicantsRepository.save(response);

        redirectAttributes.addFlashAttribute("success", "Вы успешно откликнулись на вакансию!");
        return "redirect:/vacancies/" + vacancyId;
    }

}
