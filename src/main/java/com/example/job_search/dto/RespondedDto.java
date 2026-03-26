package com.example.job_search.dto;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespondedDto {

    @Positive
    private Integer resumeId;
    @Positive
    private Integer vacancyId;

    private boolean confirmation;
}
