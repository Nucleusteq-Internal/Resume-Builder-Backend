package com.project.ResumeBuilder.repository;

import com.project.ResumeBuilder.dtos.EmployeeDetailDTO;
import com.project.ResumeBuilder.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String userEmail);

    @Query("SELECT new com.project.ResumeBuilder.dtos.EmployeeDetailDTO(u.userId, u.email, u.name) FROM Users u WHERE u.role = 'ROLE_EMPLOYEE'")
    List<EmployeeDetailDTO> findEmployees();

    boolean existsByEmpId(String empId);
    boolean existsByEmail(String email);

}
