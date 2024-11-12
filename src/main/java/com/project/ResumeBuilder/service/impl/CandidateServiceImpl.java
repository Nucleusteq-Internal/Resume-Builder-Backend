package com.project.ResumeBuilder.service.impl;

import com.project.ResumeBuilder.constants.ProfileConstants;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.entities.CandidateProfile;
import com.project.ResumeBuilder.entities.Profile;
import com.project.ResumeBuilder.entities.Users;
import com.project.ResumeBuilder.exception.NotFoundException;
import com.project.ResumeBuilder.repository.CandidateRepository;
import com.project.ResumeBuilder.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {


    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CommonResponseDto createCandidateProfile(Long id,CandidateDto candidateDto) {

        CandidateProfile candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + id));


       // candidate.setName(candidateDto.getName());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setContactNo(candidateDto.getContactNo());
        candidate.setObjective(candidateDto.getObjective());
        candidate.setProfileData(candidateDto.getProfileData());
        candidate.setCreatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);
        CommonResponseDto message=new CommonResponseDto();
        message.setMessage(ProfileConstants.PROFILE_CREATED_SUCCESSFULLY);
        return message;
    }

    public NameResponseDto createName(@RequestBody NameDto nameDto) {
        //JobTitle jobTitle = new JobTitle();



        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName(nameDto.getName());
        candidateRepository.save(candidateProfile);
        NameResponseDto responseDto=new NameResponseDto();
        responseDto.setId(candidateProfile.getId());

        return responseDto;



    }



  /*  private CandidateResponseDto convertToResponseDto(CandidateProfile candidate) {
        CandidateResponseDto responseDto = new CandidateResponseDto();
        responseDto.setId(candidate.getId());
        responseDto.setName(candidate.getName());
        responseDto.setContactNo(candidate.getContactNo());
        responseDto.setObjective(candidate.getObjective());
        responseDto.setCreatedAt(candidate.getCreatedAt());
        responseDto.setProfileData(candidate.getProfileData());
        responseDto.setIsDeleted(candidate.getIsDeleted());

        return responseDto;
    }*/


}
