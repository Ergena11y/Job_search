package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Messages {
    private Integer id;
    private Integer respondedApplicants;
    private String content;
    private LocalDateTime timeStamp;
}
