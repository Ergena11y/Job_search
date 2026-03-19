package com.example.job_search.controller;



import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.service.RespondedApplicantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responds")
@RequiredArgsConstructor
public class RespondedApplicantsController {

    private final RespondedApplicantsService respondedApplicantsService;

    @GetMapping
    public List<RespondedApplicants> getAll(){
        return respondedApplicantsService.getAll();
    }

    @GetMapping("vacancy/{vacancyId}")
    public List<RespondedApplicants> getByVacancy(@PathVariable int vacancyId){
        return respondedApplicantsService.getByVacancyId(vacancyId);
    }

    @PostMapping
    public void  respond(@RequestBody RespondedApplicants responded){
        respondedApplicantsService.respond(responded);
    }
}
