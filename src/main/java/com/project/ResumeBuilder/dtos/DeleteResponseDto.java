package com.project.ResumeBuilder.dtos;

public class DeleteResponseDto {

    private String message;
    private Boolean isDeleted;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
