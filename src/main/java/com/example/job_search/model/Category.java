package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
}
