package com.project.ResumeBuilder.controller;
import com.project.ResumeBuilder.dtos.CandidateDto;
import com.project.ResumeBuilder.dtos.CommonResponseDto;
import com.project.ResumeBuilder.dtos.ProfileDto;
import com.project.ResumeBuilder.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate-profiles")
public class CandidateController {

    @Autowired
    private CandidateService profileService;


    @PostMapping("/create")
    public ResponseEntity<CommonResponseDto> createCandidateProfile(@RequestBody CandidateDto candidateDto) {
        System.out.println(candidateDto);
        CommonResponseDto createdProfile = profileService.createCandidateProfile(candidateDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }
}
