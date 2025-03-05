package com.project.ResumeBuilder.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.project.ResumeBuilder.constants.ConstantMessage.MIN_PASSWORD_LENGTH;

@Data
public class ChangePasswordDto {
    @NotBlank(message = "Password must not be empty")
    private String currentPassword;
    @NotBlank(message = "New password must not be empty")
    @Size(min = MIN_PASSWORD_LENGTH, message = "Password should be a minimum of 8 characters")
    private String newPassword;

}
