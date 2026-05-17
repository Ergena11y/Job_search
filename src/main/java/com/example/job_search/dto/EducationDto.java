package com.example.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationDto {

    @NotBlank
    private String institution;

    @NotBlank
    private String program;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String degree;
}
