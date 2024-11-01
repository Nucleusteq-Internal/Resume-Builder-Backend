package com.project.ResumeBuilder.service;
import com.project.ResumeBuilder.dtos.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ProfileService {

    CommonResponseDto createProfile(Long id,ProfileDto profileDto);
    CommonResponseDto updateProfile(Long id, @Valid ProfileUpdateDto profileDto);
    ProfileResponseDto getProfileById(Long id);
    DeleteResponseDto deleteProfile(Long profileId);
    List<ProfileResponseDto> getAllProfiles();
    List<ProfileResponseDto> getProfilesByUserId(Long userId);
    JobTitleResponseDto createJobTitle(JobTitleDto jobTitleDto);
    List<String> getAllCollegeNames();



}

