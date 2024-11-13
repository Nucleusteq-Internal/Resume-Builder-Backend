package com.project.ResumeBuilder.repository;

import com.project.ResumeBuilder.entities.Profile;
import com.project.ResumeBuilder.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  /* List<Profile> findAllByUserId(Long userId);*/
  @Query("SELECT p FROM Profile p WHERE p.user.id = :userId")
  List<Profile> findAllByUserId(Long userId);

  /*Optional<Profile> findByUser_UserIdAndJobTitle(Long UserId, String jobTitle);*/

}

