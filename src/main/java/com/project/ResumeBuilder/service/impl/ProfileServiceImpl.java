package com.project.ResumeBuilder.service.impl;
import com.project.ResumeBuilder.constants.ProfileConstants;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.entities.Profile;
import com.project.ResumeBuilder.entities.Users;
import com.project.ResumeBuilder.exception.NotFoundException;
import com.project.ResumeBuilder.exception.ResourceConflictException;
import com.project.ResumeBuilder.repository.ProfileRepository;
import com.project.ResumeBuilder.repository.UserRepository;
import com.project.ResumeBuilder.service.ProfileService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponseDto createProfile(Long id, ProfileDto profileDto) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + id));

        //profile.setUserId(profileDto.getUserId());
        profile.setProfileName(profileDto.getProfileName());
        profile.setContactNo(profileDto.getContactNo());
        profile.setObjective(profileDto.getObjective());
        profile.setProfileData(profileDto.getProfileData());
        profile.setCreatedAt(LocalDateTime.now());
        profileRepository.save(profile);
        CommonResponseDto message=new CommonResponseDto();
        message.setMessage(ProfileConstants.PROFILE_CREATED_SUCCESSFULLY);
        return message;}


    @Override
    public CommonResponseDto updateProfile(Long id, ProfileUpdateDto profileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + id));

        profile.setProfileName(profileDto.getProfileName());
        profile.setContactNo(profileDto.getContactNo());
        profile.setObjective(profileDto.getObjective());
        profile.setProfileData(profileDto.getProfileData());
        profileRepository.save(profile);
        CommonResponseDto message=new CommonResponseDto();
        message.setMessage(ProfileConstants.PROFILE_UPDATED_SUCCESSFULLY);
        return message;
    }

    @Override
    public ProfileResponseDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + id));

        ProfileResponseDto responseDto =  convertToResponseDto(profile);
        return responseDto;
    }

    @Override
    public DeleteResponseDto deleteProfile(Long profileId) {
        Optional<Profile> profileOptional = profileRepository.findById(profileId);
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            profile.setIsDeleted(true);
            profile.setDeletedAt(LocalDateTime.now());
            profileRepository.save(profile);

            DeleteResponseDto response=new DeleteResponseDto();
            response.setMessage(ProfileConstants.PROFILE_DELETED_SUCCESSFULLY);
            response.setIsDeleted(profile.getIsDeleted());
            return response;
        } else {
            throw new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + profileId);
        }


    }
    public List<ProfileResponseDto> getProfilesByUserId(Long userId) {
      List<Profile> profiles = profileRepository.findAllByUserId(userId);

      if (profiles.isEmpty()) {
          throw new NotFoundException(ProfileConstants.USER_NOT_FOUND + userId);
      }

      return profiles.stream()
              .map(this::convertToResponseDto)
              .collect(Collectors.toList());
  }


    public List<ProfileResponseDto> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    private ProfileResponseDto convertToResponseDto(Profile profile) {
        ProfileResponseDto responseDto = new ProfileResponseDto();
        responseDto.setId(profile.getId());
        if (profile.getUser() != null) {
            responseDto.setUserId(profile.getUser().getUserId());
        }
        responseDto.setProfileName(profile.getProfileName());
        responseDto.setContactNo(profile.getContactNo());
        responseDto.setObjective(profile.getObjective());
        responseDto.setCreatedAt(profile.getCreatedAt());
        responseDto.setProfileData(profile.getProfileData());
        responseDto.setIsDeleted(profile.getIsDeleted());
        responseDto.setJobTitle(profile.getJobTitle());
        return responseDto;
    }


    public JobTitleResponseDto createJobTitle(@RequestBody JobTitleDto jobTitleDto) {
        //JobTitle jobTitle = new JobTitle();

        Optional<Users> userOptional = userRepository.findById(jobTitleDto.getUserId());
        if (!userOptional.isPresent()) {
            throw new NotFoundException(ProfileConstants.USER_NOT_FOUND + jobTitleDto.getUserId());
        }

       Optional<Profile> existingProfile = profileRepository.findByUser_UserIdAndJobTitle(jobTitleDto.getUserId(), jobTitleDto.getTitle());
        if (existingProfile.isPresent()) {
            throw new ResourceConflictException(ProfileConstants.JOBTITLE_ALREADY_EXISTS + jobTitleDto.getUserId());
        }

        Profile profile = new Profile();
        profile.setJobTitle(jobTitleDto.getTitle());
        profile.setUser(userOptional.get());
        profileRepository.save(profile);
        JobTitleResponseDto jobTitle = new JobTitleResponseDto();
        jobTitle.setId(profile.getId());
        return jobTitle;

    }

  /*  public List<String> getAllCollegeNames() {
        return profileRepository.findAll().stream()
                .filter(profile -> profile.getProfileData() != null)
                .flatMap(profile -> profile.getProfileData().getEducation().stream())
                .map(EducationDto::getCollegeName)
                .collect(Collectors.toList());
    }*/


}