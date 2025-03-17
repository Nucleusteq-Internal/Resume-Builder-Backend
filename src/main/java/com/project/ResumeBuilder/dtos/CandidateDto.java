package com.project.ResumeBuilder.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CandidateDto {

    @NotNull(message = "User ID cannot be empty")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long id;

    private String email;

    @NotEmpty(message = "Contact number cannot be empty")
    private String contactNo;

    @NotEmpty(message = "Objective cannot be empty")
    private String objective;

    private String name;

    @Valid
    private ProfileDataDto profileData;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProfileDto{" +

                ", contactNo='" + contactNo + '\'' +
                ", objective='" + objective + '\'' +
                ", email='" + email + '\'' +
                ", profileData=" + profileData +
                '}';
    }
}



