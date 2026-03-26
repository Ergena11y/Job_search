package com.example.job_search.dto;


import jakarta.validation.constraints.*;
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
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    @Min(18)
    @Max(75)
    @NotNull
    private Integer age;
    @Email
    @NotBlank
    private String email;
    private String phoneNumber;
    private String avatar;
    private String accountType;
}
