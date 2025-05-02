package com.project.ResumeBuilder.service.impl;

import com.project.ResumeBuilder.constants.ProfileConstants;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.entities.CandidateProfile;
import com.project.ResumeBuilder.exception.NotFoundException;
import com.project.ResumeBuilder.exception.ResourceNotFoundException;
import com.project.ResumeBuilder.repository.CandidateRepository;
import com.project.ResumeBuilder.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {


    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CommonResponseDto updateCandidateProfile(Long id,CandidateDto candidateDto) {

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

    @Override
    public CommonResponseDto uploadCandidateProfile(CandidateDto candidateDto) {

        CandidateProfile candidate =new CandidateProfile();
        candidate.setName(candidateDto.getName());
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
    @Override
    public DeleteResponseDto deleteCandidateProfile(Long candidateId) {
        CandidateProfile candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + candidateId));

        candidate.setIsDeleted(true);
        candidate.setDeletedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        DeleteResponseDto response = new DeleteResponseDto();
        response.setMessage(ProfileConstants.PROFILE_DELETED_SUCCESSFULLY);
        response.setIsDeleted(candidate.getIsDeleted());
        return response;
    }

    @Override
    public List<CandidateResponseDto> getProfilesBySeries(String series){
        try{
            List<CandidateProfile> candidates =  candidateRepository.findBySeries(series);
            if (!candidates.isEmpty())
            {
                return candidates.stream().map(this::convertToResponseDto).collect(Collectors.toList());
            }
            else throw new ResourceNotFoundException("No Candidates Found");
        }
        catch (ResourceNotFoundException e) {
            throw e; // Re-throwing NotFoundException to maintain original exception
        }
    }

    @Override
    public PaginatedResponse<CandidateResponseDto> getAllProfilesWithPagination(int page, int size, String searchQuery) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<CandidateProfile> candidatePage;
        if (searchQuery == null || searchQuery.isEmpty()) {
            // Fetch all profiles if no search query is provided
            candidatePage = candidateRepository.findByIsDeletedFalse(pageable);
        } else {
            // Fetch filtered profiles based on the search query
            candidatePage = candidateRepository.findByIsDeletedFalseAndNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    searchQuery, searchQuery, pageable);
        }
        List<CandidateResponseDto> candidateResponseList = candidatePage.getContent()
                .stream()
                .map(this::convertToResponseDto)  // your own mapper method
                .collect(Collectors.toList());

        PaginatedResponse.Pagination pagination = new PaginatedResponse.Pagination(
                candidatePage.getTotalElements(),
                size,
                page,
                candidatePage.getTotalPages()
        );

        return new PaginatedResponse<>(candidateResponseList, pagination);
    }


    public NameResponseDto createName(@RequestBody NameDto nameDto) {

        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setName(nameDto.getName());
        candidateRepository.save(candidateProfile);
        NameResponseDto responseDto=new NameResponseDto();
        responseDto.setId(candidateProfile.getId());

        return responseDto;



    }

   public List<CandidateResponseDto> getAllProfiles() {
        List<CandidateProfile> candidate = candidateRepository.findAll();
        return candidate.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public CandidateResponseDto getCandidateProfileById(Long id) {
        CandidateProfile profile = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProfileConstants.PROFILE_NOT_FOUND + id));

        CandidateResponseDto responseDto =  convertToResponseDto(profile);
        return responseDto;
    }


    private CandidateResponseDto convertToResponseDto(CandidateProfile candidate) {
        CandidateResponseDto responseDto = new CandidateResponseDto();
        responseDto.setId(candidate.getId());
        responseDto.setName(candidate.getName());
        responseDto.setEmail(candidate.getEmail());
        responseDto.setContactNo(candidate.getContactNo());
        responseDto.setObjective(candidate.getObjective());
        responseDto.setCreatedAt(candidate.getCreatedAt());
        responseDto.setProfileData(candidate.getProfileData());
        responseDto.setIsDeleted(candidate.getIsDeleted());

        return responseDto;
    }


}
