package com.project.ResumeBuilder.controller;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.service.CandidateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate-profiles")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/upload")
    public ResponseEntity<CommonResponseDto> uploadCandidateProfile(@RequestBody CandidateDto candidateDto) {
        System.out.println(candidateDto);
        CommonResponseDto createdProfile = candidateService.uploadCandidateProfile(candidateDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }


    @PutMapping("/create/{id}")
    public ResponseEntity<CommonResponseDto> createCandidateProfile(@PathVariable Long id,@RequestBody CandidateDto candidateDto) {
        System.out.println(candidateDto);
        CommonResponseDto createdProfile = candidateService.createCandidateProfile(id,candidateDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/delete")
    public  ResponseEntity<DeleteResponseDto> deleteCandidateProfile(@PathVariable Long id)
    {
        DeleteResponseDto response   = candidateService.deleteCandidateProfile(id);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/createName")
    public ResponseEntity<NameResponseDto> createName(@RequestBody NameDto nameDto) {
        NameResponseDto name=candidateService.createName(nameDto);
        return new ResponseEntity<>(name, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> getCandidateProfileById(@PathVariable Long id) {
        CandidateResponseDto response = candidateService.getCandidateProfileById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/getAllProfile")
    public ResponseEntity<List<CandidateResponseDto>> getAllProfiles() {
        List<CandidateResponseDto> profiles = candidateService.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}
