package com.project.ResumeBuilder.service;

import com.project.ResumeBuilder.dtos.*;

import java.util.List;

public interface CandidateService {

    CommonResponseDto createCandidateProfile(Long id,CandidateDto candidateDto);
    CandidateResponseDto getCandidateProfileById(Long id);
    NameResponseDto createName(NameDto nameDto);
    List<CandidateResponseDto> getAllProfiles();
}
