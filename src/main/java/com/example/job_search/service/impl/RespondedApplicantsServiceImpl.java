package com.example.job_search.service.impl;


import com.example.job_search.dao.RespondedApplicantsDao;
import com.example.job_search.dto.RespondedDto;
import com.example.job_search.dto.UserDto;
import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.service.RespondedApplicantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RespondedApplicantsServiceImpl implements RespondedApplicantsService {

    private final RespondedApplicantsDao respondedApplicantsDao;

    @Override
    public List<RespondedDto> getAll()  {
        return respondedApplicantsDao.getAll().stream()
                .map(this::mapToRespondedDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getApplicantsByVacancyId(int vacancyId) {
        return respondedApplicantsDao.getUsersByVacancyId(vacancyId).stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void respond(RespondedApplicants responded) {
        respondedApplicantsDao.respond(responded);
    }

    private RespondedDto mapToRespondedDto(RespondedApplicants model) {
        return RespondedDto.builder()
                .resumeId(model.getResumeId())
                .vacancyId(model.getVacancyId())
                .confirmation(model.isConfirmation())
                .build();
    }
}
