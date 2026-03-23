package com.example.job_search.service;

import com.example.job_search.dto.RespondedDto;
import com.example.job_search.dto.UserDto;


import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedDto> getAll();
    List<UserDto> getApplicantsByVacancyId(int vacancyId);
    void respond(RespondedDto respondedDto);
}
