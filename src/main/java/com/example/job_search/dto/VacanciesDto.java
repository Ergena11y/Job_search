package com.example.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


    private Integer categoryId;
    private String categoryName;

    @NotNull
    @PositiveOrZero(message =  "Salary must be >= 0")
    private float salary;

    @NotNull
    private Integer expFrom;

    @NotNull
    @PositiveOrZero
    private Integer expTo;

    private Boolean isActive;


    private Integer authorId;
    private String authorName;
    private String authorEmail;


    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    public String getFormattedUpdateTime() {
        if (updateTime == null) return "";
        return updateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getFormattedCreatedDate() {
        if (createdDate == null) return "";
        return createdDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}
