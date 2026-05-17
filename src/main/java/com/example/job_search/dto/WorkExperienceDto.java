package com.example.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceDto {
    @NotNull
    @Positive
    private Integer years;

    @NotBlank
    private String companyName;

    @NotBlank
    private String position;

    private String responsibilities;
}
