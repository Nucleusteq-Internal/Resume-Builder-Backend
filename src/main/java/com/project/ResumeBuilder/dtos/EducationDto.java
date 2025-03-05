package com.project.ResumeBuilder.dtos;
import jakarta.validation.constraints.NotEmpty;

public class EducationDto {

    @NotEmpty(message = "Course cannot be empty")
    private String course;

    @NotEmpty(message = "College name cannot be empty")
    private String collegeName;

    @NotEmpty(message = "Duration cannot be empty")
    private String duration;


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public  String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public  String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}

