package com.project.ResumeBuilder.dtoconvertor;

import com.project.ResumeBuilder.dtos.UserProfileDetailsOutDTO;
import com.project.ResumeBuilder.entities.Users;
import com.project.ResumeBuilder.dtos.RegisterInDTO;
import com.project.ResumeBuilder.dtos.LoginOutDTO;
import com.project.ResumeBuilder.dtos.UserOutDTO;

public class DtoConvertor{

    public static LoginOutDTO convertToLoginResponse(Users users) {
        LoginOutDTO loginOutDTO = new LoginOutDTO();
        loginOutDTO.setUserId(users.getUserId());
        loginOutDTO.setEmail(users.getEmail());
        loginOutDTO.setName(users.getName());
        loginOutDTO.setRole(users.getRole());
        return loginOutDTO;
    }

    public static UserOutDTO convertToUserOutDTO(Users users) {
        UserOutDTO userOutDTO = new UserOutDTO();
        userOutDTO.setUserId(users.getUserId());
        userOutDTO.setName(users.getName());
        userOutDTO.setEmail(users.getEmail());
        userOutDTO.setRole(users.getRole());
        return userOutDTO;
    }

    public static UserProfileDetailsOutDTO convertToUserProfileDetailsOutDTO(Users users) {
        UserProfileDetailsOutDTO userProfileDetailsOutDTO = new UserProfileDetailsOutDTO();
        userProfileDetailsOutDTO.setAddress(users.getAddress());
        userProfileDetailsOutDTO.setDob(users.getDob());
        userProfileDetailsOutDTO.setBio(users.getBio());
        userProfileDetailsOutDTO.setPhone(users.getPhone());
        if (users.getGender() != null) {
            userProfileDetailsOutDTO.setGender(users.getGender().name());
        }
        return userProfileDetailsOutDTO;
    }

    public static Users convertToEntity(RegisterInDTO registerInDTO) {
        Users users = new Users();
        users.setEmail(registerInDTO.getEmail());
        users.setName(registerInDTO.getName());
        users.setPassword(registerInDTO.getPassword());
        return users;
    }
}
