package com.example.job_search.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespondedDto {
    private Integer resumeId;
    private Integer vacancyId;
    private boolean confirmation;
}
