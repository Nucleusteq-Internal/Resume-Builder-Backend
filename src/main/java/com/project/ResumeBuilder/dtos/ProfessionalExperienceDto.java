package com.project.ResumeBuilder.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public class ProfessionalExperienceDto {

    @NotEmpty(message = "Job title cannot be empty")
    private String jobTitle;

    @NotEmpty(message = "Company name cannot be empty")
    private String companyName;

    @NotEmpty(message = "Project name cannot be empty")
    private String projectName;


    @Pattern(regexp = "\\d{4}-\\d{2}", message = "Start date must be in YYYY-MM format")
    private String startDate;

    @Pattern(regexp = "\\d{4}-\\d{2}", message = "End date must be in YYYY-MM format")
    private String endDate;

    @NotEmpty(message = "TechStack name cannot be empty")
    private String techStack;
    @NotEmpty(message = "Details cannot be empty")
    private String details;


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public  String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public  String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}
