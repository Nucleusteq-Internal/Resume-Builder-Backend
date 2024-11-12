package com.project.ResumeBuilder.service;

import com.project.ResumeBuilder.dtos.*;

public interface CandidateService {

    CommonResponseDto createCandidateProfile(Long id,CandidateDto candidateDto);
   /* CandidateResponseDto getCandidateProfileById(Long id);*/
    NameResponseDto createName(NameDto nameDto);
}
