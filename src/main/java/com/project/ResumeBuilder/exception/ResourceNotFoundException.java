package com.project.ResumeBuilder.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message){
         super(message);
    }
}
