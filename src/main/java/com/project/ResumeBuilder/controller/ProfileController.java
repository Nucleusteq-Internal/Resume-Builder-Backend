package com.project.ResumeBuilder.controller;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.service.ProfileService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user-profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @PutMapping("/create/{id}")
    public ResponseEntity<CommonResponseDto> createProfile(@PathVariable Long id,@RequestBody ProfileDto profileDto) {
        CommonResponseDto createdProfile = profileService.createProfile(id,profileDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommonResponseDto> updateProfile(@PathVariable Long id, @RequestBody ProfileUpdateDto profileUpdateDto) {
        CommonResponseDto updatedProfile = profileService.updateProfile(id, profileUpdateDto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long id) {
        ProfileResponseDto response = profileService.getProfileById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<CommonResponseDto> deleteProfile(@PathVariable Long id) {
        CommonResponseDto response=  profileService.deleteProfile(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProfileResponseDto>> getProfilesByUserId(@PathVariable Long userId) {
        List<ProfileResponseDto> profileResponse = profileService.getProfilesByUserId(userId);
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/getAllProfile")
    public ResponseEntity<List<ProfileResponseDto>> getAllProfiles() {
        List<ProfileResponseDto> profiles = profileService.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PostMapping("/createJobTitle")
    public ResponseEntity<JobTitleResponseDto> createJobTitle(@RequestBody JobTitleDto jobTitleDto) {
        JobTitleResponseDto jobTitle=profileService.createJobTitle(jobTitleDto);
        return new ResponseEntity<>(jobTitle, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/colleges")
    public List<String> getAllCollegeNames() {
        return profileService.getAllCollegeNames();
    }



}


