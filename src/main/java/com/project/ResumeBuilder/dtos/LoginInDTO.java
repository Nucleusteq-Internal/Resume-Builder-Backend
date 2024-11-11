package com.project.ResumeBuilder.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
public class LoginInDTO {

    @Email
    @NotBlank(message = "Valid Email not found")
    @Pattern(
            regexp = "^[\\w.%+-]+@(gmail|nucleusteq)\\.com$",
            message = "Valid Email not found"
    )
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
