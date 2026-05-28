package com.example.job_search.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceDto {
    @Min(value = 0, message = "Опыт работы не может быть отрицательным")
    private Integer years;

    private String companyName;

    private String position;

    private String responsibilities;
}
