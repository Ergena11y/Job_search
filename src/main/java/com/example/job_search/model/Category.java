package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Category {
    private int id;
    private String name;
    private Category parentId;
}
