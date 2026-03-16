package com.example.job_search.controller;



import com.example.job_search.model.RespondedApplicants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responds")
@RequiredArgsConstructor
public class RespondedApplicantsController {

    @GetMapping
    public List<RespondedApplicants> getAll(){
        return List.of();
    }

    @GetMapping("vacancy/{vacancyId}")
    public List<RespondedApplicants> getByVacancy(@PathVariable int vacancyId){
        return List.of();
    }

    @PostMapping
    public void  respond(@RequestBody RespondedApplicants responded){}
}
