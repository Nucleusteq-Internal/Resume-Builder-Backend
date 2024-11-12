package com.project.ResumeBuilder.service;

import com.project.ResumeBuilder.dtos.CandidateDto;
import com.project.ResumeBuilder.dtos.CommonResponseDto;
import com.project.ResumeBuilder.dtos.ProfileDto;

public interface CandidateService {

    CommonResponseDto createCandidateProfile(CandidateDto candidateDto);
}
