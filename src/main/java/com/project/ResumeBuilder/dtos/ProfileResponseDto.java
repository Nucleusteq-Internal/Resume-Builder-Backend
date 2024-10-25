package com.project.ResumeBuilder.dtos;



import jakarta.validation.Valid;

import java.time.LocalDateTime;

public class ProfileResponseDto {

    private Long id;
    private Integer userId;
    private String profileName;
    private String contactNo;
    private String objective;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
    private String jobTitle;
    // private LocalDateTime updatedAt;

    @Valid
    private ProfileDataDto profileData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
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

    public @Valid ProfileDataDto getProfileData() {
        return profileData;
    }

    public void setProfileData(@Valid ProfileDataDto profileData) {
        this.profileData = profileData;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}

