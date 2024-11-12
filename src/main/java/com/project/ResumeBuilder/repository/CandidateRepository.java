package com.project.ResumeBuilder.repository;


import com.project.ResumeBuilder.entities.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateProfile,Long> {


}
