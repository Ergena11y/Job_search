package com.example.job_search.common;

import com.example.job_search.dto.EducationDto;
import com.example.job_search.dto.WorkExperienceDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResumeFormParser {

    public List<WorkExperienceDto> parseWorkExperience(HttpServletRequest req) {
        List<WorkExperienceDto> list = new ArrayList<>();
        String[] companies = req.getParameterValues("work_company[]");
        if (companies == null) return list;

        String[] years     = req.getParameterValues("work_years[]");
        String[] positions = req.getParameterValues("work_position[]");
        String[] resps     = req.getParameterValues("work_responsibilities[]");

        for (int i = 0; i < companies.length; i++) {
            WorkExperienceDto dto = new WorkExperienceDto();
            dto.setCompanyName(companies[i]);
            dto.setYears(years != null && i < years.length && !years[i].isBlank()
                    ? Integer.parseInt(years[i]) : null);
            dto.setPosition(positions != null && i < positions.length ? positions[i] : null);
            dto.setResponsibilities(resps != null && i < resps.length ? resps[i] : null);
            list.add(dto);
        }
        return list;
    }

    public List<EducationDto> parseEducation(HttpServletRequest req) {
        List<EducationDto> list = new ArrayList<>();
        String[] institutions = req.getParameterValues("edu_institution[]");
        if (institutions == null) return list;

        String[] programs  = req.getParameterValues("edu_program[]");
        String[] starts    = req.getParameterValues("edu_start[]");
        String[] ends      = req.getParameterValues("edu_end[]");
        String[] degrees   = req.getParameterValues("edu_degree[]");

        for (int i = 0; i < institutions.length; i++) {
            EducationDto dto = new EducationDto();
            dto.setInstitution(institutions[i]);
            dto.setProgram(programs != null && i < programs.length ? programs[i] : null);
            dto.setDegree(degrees != null && i < degrees.length ? degrees[i] : null);
            if (starts != null && i < starts.length && !starts[i].isBlank())
                dto.setStartDate(LocalDate.parse(starts[i]));
            if (ends != null && i < ends.length && !ends[i].isBlank())
                dto.setEndDate(LocalDate.parse(ends[i]));
            list.add(dto);
        }
        return list;
    }

}
