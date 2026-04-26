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

    private  Long id;

    @NotBlank
    @Size(min = 6, max = 40)
    private String name;

    @NotBlank
    @Size(min = 10, max = 150)
    private String description;

    @NotNull
    private Integer categoryId;

    @NotNull
    @PositiveOrZero(message =  "Salary must be >= 0")
    private float salary;

    @NotNull
    private Integer expFrom;

    @NotNull
    @PositiveOrZero
    private Integer expTo;

    private Boolean isActive;

    @NotNull
    private Integer authorId;


    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
