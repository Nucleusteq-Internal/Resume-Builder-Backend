package com.project.ResumeBuilder.controller;


import com.project.ResumeBuilder.dtos.*;
import com.project.ResumeBuilder.service.EmailService;
import com.project.ResumeBuilder.service.OtpService;
import com.project.ResumeBuilder.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<List<SuccessOutDTO>> registerUsers(@Valid @RequestBody List<RegisterInDTO> registerInDTOList) {
        List<SuccessOutDTO> responseList = new ArrayList<>();
        for (RegisterInDTO registerInDTO : registerInDTOList) {
            String response = usersService.register(registerInDTO);
            responseList.add(new SuccessOutDTO(response));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginOutDTO> login(@Valid @RequestBody LoginInDTO loginInDTO) {
        LoginOutDTO loginOutDTO =  usersService.login(loginInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginOutDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutDTO> findById(@PathVariable("id") long userId) {
        UserOutDTO user = usersService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileDetailsOutDTO> findUserProfile(@PathVariable("id") long userId) {
        UserProfileDetailsOutDTO user = usersService.findUserProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserOutDTO>> getAllUser() {
        List<UserOutDTO> userRespons = usersService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userRespons);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<SuccessOutDTO> updateUser(@PathVariable("userId") long usedId, @RequestBody @Valid UpdateUserInDTO updateUserInDTO) {
        String response = usersService.updateUser(usedId, updateUserInDTO);
        SuccessOutDTO successOutDTO = new SuccessOutDTO(response);
        return ResponseEntity.status(HttpStatus.OK).body(successOutDTO);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<SuccessOutDTO> forgotPassword(@RequestParam String email) {
        String response = usersService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessOutDTO(response));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<SuccessOutDTO> resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        String response = usersService.resetPassword(email,otp,newPassword);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessOutDTO(response));
    }

    @PostMapping("/{userId}/change-password")
    public ResponseEntity<SuccessOutDTO> changePassword(
            @PathVariable("userId") long userId,
            @Valid @RequestBody ChangePasswordDto changePasswordDTO) {
        String response = usersService.changePassword(userId, changePasswordDTO);
        SuccessOutDTO successOutDTO = new SuccessOutDTO(response);
        return ResponseEntity.status(HttpStatus.OK).body(successOutDTO);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDetailDTO>> getEmployees() {
        List<EmployeeDetailDTO> employeeDetailDTOS = usersService.getEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDetailDTOS);
    }

    @PostMapping("/upload-excel-data")
    public ResponseEntity<String> uploadExcelData(@RequestBody @Valid List<BulkUserDto> userList) {
        usersService.saveUsers(userList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Users saved successfully");
    }
}
