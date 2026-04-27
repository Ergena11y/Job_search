package com.example.job_search.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class UpdateProfileDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private MultipartFile avatar;
}
