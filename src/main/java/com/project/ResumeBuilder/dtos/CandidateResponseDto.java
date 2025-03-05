package com.project.ResumeBuilder.dtos;

import jakarta.validation.Valid;

import java.time.LocalDateTime;


public class CandidateResponseDto {

    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private String objective;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
    // private LocalDateTime updatedAt;

    @Valid
    private ProfileDataDto profileData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public @Valid ProfileDataDto getProfileData() {
        return profileData;
    }

    public void setProfileData(@Valid ProfileDataDto profileData) {
        this.profileData = profileData;
    }
}
