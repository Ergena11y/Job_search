package com.example.job_search.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String sureName;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String avatar;
}
