package com.project.ResumeBuilder.controller;

import com.project.ResumeBuilder.dtos.CandidateResponseDto;
import com.project.ResumeBuilder.dtos.PaginatedResponse;
import com.project.ResumeBuilder.entities.CandidateProfile;
import com.project.ResumeBuilder.service.CandidateService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserProfileController {
    private final CandidateService candidateService;

    public UserProfileController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    @Transactional
    @GetMapping("/getAllProfilesWithPagination")
    public ResponseEntity<?> getAllProfilesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "") String searchQuery){
        PaginatedResponse<CandidateResponseDto> response = candidateService.getAllProfilesWithPagination(page, size, searchQuery);
        return ResponseEntity.ok(response);
    }
}
