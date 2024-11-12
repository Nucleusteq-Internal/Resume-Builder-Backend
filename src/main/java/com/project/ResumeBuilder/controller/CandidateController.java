package com.project.ResumeBuilder.controller;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate-profiles")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;


    @PutMapping("/create/{id}")
    public ResponseEntity<CommonResponseDto> createCandidateProfile(@PathVariable Long id,@RequestBody CandidateDto candidateDto) {
        System.out.println(candidateDto);
        CommonResponseDto createdProfile = candidateService.createCandidateProfile(id,candidateDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }
    @PostMapping("/createName")
    public ResponseEntity<NameResponseDto> createName(@RequestBody NameDto nameDto) {
        NameResponseDto name=candidateService.createName(nameDto);
        return new ResponseEntity<>(name, HttpStatus.CREATED);
    }
}
