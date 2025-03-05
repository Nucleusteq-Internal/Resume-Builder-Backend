package com.project.ResumeBuilder.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class ProfileDto {

   // @NotNull(message = "User ID cannot be empty")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;

    @NotEmpty(message = "Profile name cannot be empty")
    @Size(max = 50, message = "Profile name cannot exceed 50 characters")
    private String profileName;

    @NotEmpty(message = "Contact number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits")
    private String contactNo;

    @NotEmpty(message = "Objective cannot be empty")
    @Size(max = 1000, message = "Objective cannot exceed 1000 characters")
    private String objective;

    @Valid
    @NotNull(message = "Profile data cannot be null")
    private ProfileDataDto profileData;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    @Override
    public String toString() {
        return "ProfileDto{" +
                "userId=" + userId +
                ", profileName='" + profileName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", objective='" + objective + '\'' +
                ", profileData=" + profileData +
                '}';
    }
}


