package com.project.ResumeBuilder.repository;


import com.project.ResumeBuilder.entities.CandidateProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateProfile,Long> {

    @Query("SELECT c FROM CandidateProfile c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :series, '%'))")
    List<CandidateProfile> findBySeries(@Param("series") String series);

    Page<CandidateProfile> findByIsDeletedFalse(Pageable pageable);
    Page<CandidateProfile> findByIsDeletedFalseAndNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name,String email,
                                                                                                      Pageable pageable);
}
