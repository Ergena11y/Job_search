package com.example.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDto {

    private Long id;


    private Long applicantId;

    private String applicantName;

    private String description;

    private String applicantEmail;

    private String applicantPhone;

    @NotBlank
    private String name;

    @Positive
    private Float salary;


    private Boolean isActive;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    @Positive
    private Integer categoryId;

    private String categoryName;

    private List<WorkExperienceDto> workExperience;

    private List<EducationDto> education;

    private String contactPhone;
    private String contactEmail;
    private String telegram;
    private String facebook;
    private String linkedin;

    public String getFormattedUpdateTime() {
        if (updateTime == null) return "";
        return updateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getFormattedCreatedDate() {
        if (createdDate == null) return "";
        return createdDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}
