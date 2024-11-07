package com.project.ResumeBuilder.service;

import com.project.ResumeBuilder.constants.ConstantMessage;
import com.project.ResumeBuilder.dtoconvertor.DtoConvertor;
import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.entities.Users;
import com.project.ResumeBuilder.enums.Gender;
import com.project.ResumeBuilder.enums.UserRole;
import com.project.ResumeBuilder.exception.ResourceConflictException;
import com.project.ResumeBuilder.exception.ResourceInvalidException;
import com.project.ResumeBuilder.exception.ResourceNotFoundException;
import com.project.ResumeBuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UsersService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String register(RegisterInDTO registerInDTO) {
        try {
            if (userRepository.findByEmail(registerInDTO.getEmail()) != null) {
                throw new ResourceConflictException(ConstantMessage.USER_ALREADY_EXISTS);
            }
            UserRole role = UserRole.valueOf(registerInDTO.getRole());
            registerInDTO.setPassword(encoder.encode(registerInDTO.getPassword()));
            Users user = DtoConvertor.convertToEntity(registerInDTO);
            user.setRole(role);
            userRepository.save(user);
            return ConstantMessage.USER_REGISTERED_SUCCESSFULLY;
        } catch (IllegalArgumentException e) {
            throw new ResourceInvalidException(ConstantMessage.VALID_ROLE_REQUIRED);
        } catch (ResourceConflictException | ResourceInvalidException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public LoginOutDTO login(LoginInDTO loginInDTO) {

        try {
           /* byte[] decodedBytes = Base64.getDecoder().decode(loginInDTO.getPassword());
            String decodedPassword = new String(decodedBytes);
            loginInDTO.setPassword(decodedPassword); */
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginInDTO.getEmail(), loginInDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                Users user = userRepository.findByEmail(loginInDTO.getEmail());
                UserRole role = user.getRole();
                LoginOutDTO loginOutDTO = DtoConvertor.convertToLoginResponse(user);
                loginOutDTO.setToken(jwtService.generateToken(loginInDTO.getEmail(), role));
                return loginOutDTO;
            }
            throw new ResourceInvalidException(ConstantMessage.INVALID_CREDENTIALS);
        } catch (ResourceInvalidException ex) {
            throw ex;
        }
    }

    public UserOutDTO findById(long userId) {
        try {
            Optional<Users> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Users users = user.get();
                UserOutDTO userOutDTO = DtoConvertor.convertToUserOutDTO(users);
                return userOutDTO;
            }
            throw new ResourceNotFoundException(ConstantMessage.USER_NOT_FOUND);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public UserProfileDetailsOutDTO findUserProfile(long userId) {
        try {
            Optional<Users> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Users users = user.get();
                UserProfileDetailsOutDTO userProfileDetailsOutDTO = DtoConvertor.convertToUserProfileDetailsOutDTO(users);
                return userProfileDetailsOutDTO;
            }
            throw new ResourceNotFoundException(ConstantMessage.USER_NOT_FOUND);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public List<UserOutDTO> findAll() {
        try {
            List<Users> users = userRepository.findAll();
            List<UserOutDTO> userOutDTOS = new ArrayList<>();
            for (Users user : users) {
                UserOutDTO userOutDTO = DtoConvertor.convertToUserOutDTO(user);
                userOutDTOS.add(userOutDTO);
            }
            if(users.isEmpty()) {
                throw new ResourceNotFoundException(ConstantMessage.USER_NOT_FOUND);
            }
            return userOutDTOS;
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public String updateUser(long userId, UpdateUserInDTO updateUserInDTO) {
        try {
            Optional<Users> user = userRepository.findById(userId);
            if(user.isPresent()) {
                Users updatedUser = user.get();
                if (!updateUserInDTO.getGender().isEmpty()) {
                    if (!Objects.equals(updateUserInDTO.getGender(), "MALE") && !Objects.equals(updateUserInDTO.getGender(), "FEMALE") && !Objects.equals(updateUserInDTO.getGender(), "OTHER")) {
                        throw new ResourceInvalidException(ConstantMessage.VALID_GENDER_REQUIRED);
                    }
                }
                updatedUser.setBio(updateUserInDTO.getBio());
                updatedUser.setAddress(updateUserInDTO.getAddress());
                updatedUser.setDob(updateUserInDTO.getDob());
                updatedUser.setPhone(updateUserInDTO.getPhone());
                Gender gender = Gender.valueOf(updateUserInDTO.getGender());
                updatedUser.setGender(gender);
                userRepository.save(updatedUser);
                return ConstantMessage.USER_UPDATED_SUCCESSFULLY;
            }
            return ConstantMessage.FAILED_TO_UPDATE_USER;
        } catch (ResourceInvalidException | ResourceConflictException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public String forgotPassword(String email) {
        try {
            Users user = userRepository.findByEmail(email);
            if (user == null) {
                throw new ResourceNotFoundException(ConstantMessage.USER_NOT_FOUND);
            }

            String otp = otpService.generateOtp(email);
            emailService.sendOtp(email, otp);
            return ConstantMessage.OTP_SENT;
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public String resetPassword(String email, String otp, String newPassword) {
        try {
            if (!otpService.validateOtp(email, otp)) {
                throw new ResourceInvalidException(ConstantMessage.INVALID_OTP);
            }

            Users user = userRepository.findByEmail(email);
            if (user == null) {
                throw new ResourceNotFoundException(ConstantMessage.USER_NOT_FOUND);
            }

            byte[] decodedBytes = Base64.getDecoder().decode(newPassword);
            String decodedPassword = new String(decodedBytes);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(decodedPassword));
            userRepository.save(user);
            otpService.clearOtp(email);
            return ConstantMessage.PASSWORD_RESET_SUCCESSFULLY;
        } catch (ResourceNotFoundException | ResourceInvalidException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }

    public String changePassword(long userId, ChangePasswordDto changePasswordDTO) {
        try {
            Optional<Users> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                byte[] decodedBytesCurrentPassword = Base64.getDecoder().decode(changePasswordDTO.getNewPassword());
                String decodedCurrentPassword = new String(decodedBytesCurrentPassword);
                if (!encoder.matches(decodedCurrentPassword, user.getPassword())) {
                    throw new ResourceConflictException(ConstantMessage.CURRENT_PASSWORD_INCORRECT);
                }
                byte[] decodedBytes = Base64.getDecoder().decode(changePasswordDTO.getNewPassword());
                String decodedPassword = new String(decodedBytes);
                user.setPassword(encoder.encode(decodedPassword));
                userRepository.save(user);
                return ConstantMessage.PASSWORD_UPDATED_SUCCESSFULLY;
            } else {
                throw new ResourceNotFoundException(ConstantMessage.USER_NOT_FOUND);
            }
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ConstantMessage.UNEXPECTED_ERROR_OCCURRED);
        }
    }
}