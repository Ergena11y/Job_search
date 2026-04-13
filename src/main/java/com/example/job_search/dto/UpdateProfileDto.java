package com.example.job_search.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProfileDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String avatar;
}
