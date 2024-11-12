package com.project.ResumeBuilder.dtos;

import jakarta.validation.constraints.NotEmpty;

public class JobTitleDto {

    @NotEmpty(message = "Job title cannot be empty")
    private String title;

    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


