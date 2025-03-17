package com.project.ResumeBuilder.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class TechnicalSkillsDto {

    @NotEmpty(message = "Technology list cannot be empty")
    private List<String> technology;

    @NotEmpty(message = "Programming list cannot be empty")
    private List<String> programming;

    @NotEmpty(message = "Tools list cannot be empty")
    private List<String> tools;

    public List<String> getTechnology() {
        return technology;
    }

    public void setTechnology(List<String> technology) {
        this.technology = technology;
    }

    public List<String> getProgramming() {
        return programming;
    }

    public void setProgramming(List<String> programming) {
        this.programming = programming;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    @Override
    public String toString() {
        return "TechnicalSkillsDto{" +
                "technology=" + technology +
                ", programming=" + programming +
                ", tools=" + tools +
                '}';
    }
}

