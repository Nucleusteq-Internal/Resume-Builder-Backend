package com.project.ResumeBuilder.dtos;

import com.project.ResumeBuilder.enums.UserRole;
import lombok.Data;

@Data
public class LoginOutDTO {

    private Long userId;
    private String email;
    private String token;
    private String name;
    private UserRole role;
}
