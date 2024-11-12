package com.project.ResumeBuilder.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDetailDTO {

    private Long userId;

    private String email;

    private String name;
}
