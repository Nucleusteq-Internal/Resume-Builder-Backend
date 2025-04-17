package com.project.ResumeBuilder.service;

import com.project.ResumeBuilder.dtos.*;

import java.util.List;

public interface CandidateService {

    CommonResponseDto updateCandidateProfile(Long id,CandidateDto candidateDto);
    CommonResponseDto uploadCandidateProfile(CandidateDto candidateDto);
    CandidateResponseDto getCandidateProfileById(Long id);
    NameResponseDto createName(NameDto nameDto);
    List<CandidateResponseDto> getAllProfiles();
    DeleteResponseDto deleteCandidateProfile(Long candidateId);

    List<CandidateResponseDto> getProfilesBySeries(String series);
}
