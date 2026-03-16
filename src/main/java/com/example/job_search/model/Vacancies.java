package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor

public class Vacancies {
    private int id;
    private String name;
    private String description;
    private Category categoryId;
    private float salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private User authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
