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



    @NotBlank
    @Size(
            min = 4,
            max = 25,
            message = "Length must be >= 4 and <= 25"
    )

    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one uppercase letter, one number"
    )
    private String password;

    private String phoneNumber;
    private String avatar;
    private String accountType;
}
