package com.project.ResumeBuilder.dtos;

import com.project.ResumeBuilder.enums.UserRole;
import lombok.Data;

@Data
public class UserOutDTO {

    private Long userId;
    private String email;
    private String name;
    private UserRole role;
}
