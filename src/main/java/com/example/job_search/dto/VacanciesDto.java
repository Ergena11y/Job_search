package com.example.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacanciesDto {
    @NotBlank
    @Size(min = 6, max = 40)
    private String name;

    @NotBlank
    @Size(min = 10, max = 150)
    private String description;
    private Integer categoryId;

    @Positive
    private float salary;

    @NotNull
    private Integer expFrom;
    @NotNull
    private Integer expTo;
    private Boolean isActive;
    private Integer authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
