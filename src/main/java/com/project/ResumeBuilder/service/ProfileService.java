package com.project.ResumeBuilder.service;
import com.project.ResumeBuilder.dtos.*;

import java.util.List;


public interface ProfileService {

    CommonResponseDto updateNewProfile(Long id, ProfileDto profileDto);
    CommonResponseDto updateProfile(Long id, ProfileUpdateDto profileDto);
    ProfileResponseDto getProfileById(Long id);
    DeleteResponseDto deleteProfile(Long profileId);
    List<ProfileResponseDto> getAllProfiles();
   List<ProfileResponseDto> getProfilesByUserId(Long userId);
    JobTitleResponseDto createJobTitle(JobTitleDto jobTitleDto);




}

