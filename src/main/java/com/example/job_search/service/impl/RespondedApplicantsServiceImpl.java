package com.example.job_search.service.impl;


import com.example.job_search.dao.RespondedApplicantsDao;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.service.RespondedApplicantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RespondedApplicantsServiceImpl implements RespondedApplicantsService {

    private final RespondedApplicantsDao respondedApplicantsDao;

    @Override
    public List<RespondedApplicants> getAll() {
        return respondedApplicantsDao.getAll();
    }

    @Override
    public List<RespondedApplicants> getByVacancyId(int vacancyId) {
        return  respondedApplicantsDao.getByVacancyId(vacancyId);
    }

    @Override
    public void respond(RespondedApplicants responded) {
        respondedApplicantsDao.respond(responded);
    }
}
