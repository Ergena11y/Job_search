package com.example.job_search.controller;



import com.example.job_search.dto.RespondedDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.service.RespondedApplicantsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responds")
@RequiredArgsConstructor
public class RespondedApplicantsController {

    private final RespondedApplicantsService respondedApplicantsService;

    @GetMapping
    public List<RespondedDto> getAll(){
        return respondedApplicantsService.getAll();
    }

    @GetMapping("vacancy/{vacancyId}")
    public List<UserDto> getByVacancy(@PathVariable int vacancyId){
        return respondedApplicantsService.getApplicantsByVacancyId(vacancyId);
    }

    @PostMapping
    public void  respond(@Valid  @RequestBody RespondedApplicants responded){
        respondedApplicantsService.respond(responded);
    }
}
