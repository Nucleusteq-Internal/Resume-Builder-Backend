package com.project.ResumeBuilder.service.impl;

import com.project.ResumeBuilder.constants.ProfileConstants;
import com.project.ResumeBuilder.dtos.CandidateDto;
import com.project.ResumeBuilder.dtos.CommonResponseDto;
import com.project.ResumeBuilder.entities.CandidateProfile;
import com.project.ResumeBuilder.repository.CandidateRepository;
import com.project.ResumeBuilder.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CandidateServiceImpl implements CandidateService {


    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CommonResponseDto createCandidateProfile(CandidateDto candidateDto) {

        CandidateProfile candidate=new CandidateProfile();

        candidate.setName(candidateDto.getName());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setContactNo(candidateDto.getContactNo());
        candidate.setObjective(candidateDto.getObjective());
        candidate.setProfileData(candidateDto.getProfileData());
        candidate.setCreatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);
        CommonResponseDto message=new CommonResponseDto();
        message.setMessage(ProfileConstants.PROFILE_CREATED_SUCCESSFULLY);
        return message;}


}
