package com.example.job_search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Integer id;

    private Integer respondedApplicantId;

    private String content;

    private LocalDateTime timeStamp;

    private String senderName;

    private String senderRole;
}
