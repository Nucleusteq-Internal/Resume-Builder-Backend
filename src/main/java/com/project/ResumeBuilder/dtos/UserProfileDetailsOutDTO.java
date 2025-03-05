package com.project.ResumeBuilder.dtos;

import com.project.ResumeBuilder.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDetailsOutDTO {

    private String address;

    private String dob;

    private String phone;

    private String bio;

    private String gender;

}
