
package com.project.ResumeBuilder.entities;

import com.project.ResumeBuilder.dtos.ProfileDataDto;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")  // Referencing 'user_id' column in 'Users' entity
  private Users user;

    @Column(name="jobTitle")
    private String jobTitle;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "contact_no")
    private String contactNo;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "objective")
    private String objective;

    @Type(JsonBinaryType.class)
    @Column(name = "profile_data", columnDefinition = "jsonb")
    private ProfileDataDto profileData; // Use ProfileDataDto instead of Map

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column
    private Boolean isDeleted = false;

    @Column
    private LocalDateTime deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    /* public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public ProfileDataDto getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileDataDto profileData) {
        this.profileData = profileData;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }





}



