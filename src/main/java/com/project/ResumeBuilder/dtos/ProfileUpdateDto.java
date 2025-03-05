package com.project.ResumeBuilder.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class ProfileUpdateDto {
    @NotEmpty(message = "Profile name is required")
    private String profileName;

    @NotEmpty(message = "Contact number is required")
    private String contactNo;

    @NotEmpty(message = "Objective is required")
    private String objective;

    @Valid
    private ProfileDataDto profileData;

    public  String getProfileName() {
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

    public  String getObjective() {
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










