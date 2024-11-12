package com.project.ResumeBuilder.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UpdateUserInDTO {

    private String address;

    private String dob;

    @Pattern(
            regexp = "^[9876]\\d{" + (9) + "}$",
            message = "Phone number should be valid"
    )
    private String phone;

    private String bio;

    private String gender;
}
