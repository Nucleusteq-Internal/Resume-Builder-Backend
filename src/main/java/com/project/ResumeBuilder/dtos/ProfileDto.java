package com.project.ResumeBuilder.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProfileDto {

    @NotNull(message = "User ID cannot be empty")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer userId;

    @NotEmpty(message = "Profile name cannot be empty")
    private String profileName;

    @NotEmpty(message = "Contact number cannot be empty")
    private String contactNo;

    @NotEmpty(message = "Objective cannot be empty")
    private String objective;

    @Valid
    private ProfileDataDto profileData;

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

    public  String getContactNo() {
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

    public ProfileDataDto getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileDataDto profileData) {
        this.profileData = profileData;
    }
}

