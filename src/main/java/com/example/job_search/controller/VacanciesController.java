package com.example.job_search.controller;

import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.exception.ForbiddenException;
import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.repository.CategoryRepository;
import com.example.job_search.repository.RespondedApplicantsRepository;
import com.example.job_search.service.ResumeService;
import com.example.job_search.service.UserService;
import com.example.job_search.service.VacancyService;
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
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController {

    private final VacancyService vacancyService;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final ResumeService resumeService;
    private final RespondedApplicantsRepository respondedApplicantsRepository;

    @GetMapping
    public String vacancies(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "date") String sortBy,
                            Principal principal,
                            Model model) {
        Page<@Valid VacanciesDto> vacancyPage =
                vacancyService.getAllVacancies(page, size, sortBy);
        model.addAttribute("vacancies", vacancyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vacancyPage.getTotalPages());
        model.addAttribute("totalItems", vacancyPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        addCurrentUser(principal, model);
        return "vacancies/vacancies";
    }

    @GetMapping("/create")
    public String createForm(Model model, Principal principal) {
        model.addAttribute("vacancyDto", new VacanciesDto());
        model.addAttribute("categories", categoryRepository.findAll());
        addCurrentUser(principal, model);
        return "vacancies/create_vacancies";
    }

    @PostMapping("/create")
    public String create(@Valid VacanciesDto dto, BindingResult br,
                         Model model, Principal principal) throws UserNotFoundException {

        if (dto.getExpFrom() != null && dto.getExpTo() != null
                && dto.getExpFrom() > dto.getExpTo()) {
            br.rejectValue("expTo", "exp.invalid",
                    "Опыт 'до' не может быть меньше опыта 'от'");
        }

        if (br.hasErrors()) {
            model.addAttribute("vacancyDto", dto);
            model.addAttribute("bindingRes", br);
            model.addAttribute("categories", categoryRepository.findAll());
            addCurrentUser(principal, model);
            return "vacancies/create_vacancies";
        }
        int userId = userService.getUserIdByEmail(principal.getName());
        dto.setAuthorId(userId);
        vacancyService.createVacancy(dto);
        return "redirect:/vacancies";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model,
                           Principal principal) throws UserNotFoundException {
        VacanciesDto vacancy = vacancyService.getById(id);
        checkOwnership(vacancy.getAuthorId(), principal);
        model.addAttribute("vacancyDto", vacancyService.getById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("id", id);
        addCurrentUser(principal, model);
        return "vacancies/edit_vacancies";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @Valid VacanciesDto dto,
                       BindingResult br, Model model,
                       Principal principal) throws UserNotFoundException {
        VacanciesDto existing = vacancyService.getById(id);
        checkOwnership(existing.getAuthorId(), principal);


        if (dto.getExpFrom() != null && dto.getExpTo() != null
                && dto.getExpFrom() > dto.getExpTo()) {
            br.rejectValue("expTo", "exp.invalid",
                    "Опыт 'до' не может быть меньше опыта 'от'");
        }

        if (br.hasErrors()) {
            model.addAttribute("vacancyDto", dto);
            model.addAttribute("bindingRes", br);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("id", id);
            addCurrentUser(principal, model);
            return "vacancies/edit_vacancies";
        }
        vacancyService.updateVacancy(id, dto);
        return "redirect:/vacancies";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, Principal principal) {
        VacanciesDto vacancy = vacancyService.getById(id);
        checkOwnership(vacancy.getAuthorId(), principal);
        vacancyService.deleteVacancy(id);
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model, Principal principal) {
        model.addAttribute("vacancy", vacancyService.getById(id));
        addCurrentUser(principal, model);

        if(principal != null ){
            try{
                int userId = userService.getUserIdByEmail(principal.getName());
                var resume = resumeService.getByApplicant(userId, 0, 100);
                model.addAttribute("myResumes", resume.getContent());
            } catch (UserNotFoundException e){
                model.addAttribute("myResumes", null);
            }
        }

        return "vacancies/vacancies_info";
    }


    @GetMapping("/{id}/responses")
    public String vacancyResponses(@PathVariable int id, Model model,
                                   Principal principal) throws UserNotFoundException {
        VacanciesDto vacancy = vacancyService.getById(id);
        int userId = userService.getUserIdByEmail(principal.getName());

        if (vacancy.getAuthorId() == null || vacancy.getAuthorId() != userId) {
            throw new ForbiddenException();
        }

        List<RespondedApplicants> responses =
                respondedApplicantsRepository.findByVacancyId(id);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("responses", responses);
        addCurrentUser(principal, model);
        return "vacancies/vacancy_responses";
    }

    private void addCurrentUser(Principal principal, Model model) {
        if (principal != null) {
            try {
                model.addAttribute("user",
                        userService.getByEmail(principal.getName()));
            } catch (UserNotFoundException e) {
                model.addAttribute("user", null);
            }
        } else {
            model.addAttribute("user", null);
        }
    }

    private void checkOwnership(Integer authorId,
                                Principal principal) throws UserNotFoundException {
        int currentUserId = userService.getUserIdByEmail(principal.getName());
        if (authorId == null || authorId != currentUserId ){
            throw new ForbiddenException();
        }
    }

}
