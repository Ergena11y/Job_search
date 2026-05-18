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
}
