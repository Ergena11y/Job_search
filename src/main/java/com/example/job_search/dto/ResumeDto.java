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
public class ResumeDto {

    private Long id;

    @NotNull
    private Long applicantId;

    private String applicantName;

    @NotBlank
    private String name;
    @Positive
    private Float salary;
    @NotNull
    private Boolean isActive;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    @Positive
    private Integer categoryId;

    private String categoryName;
}
