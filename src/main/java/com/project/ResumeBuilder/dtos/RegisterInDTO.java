package com.project.ResumeBuilder.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

import static com.project.ResumeBuilder.constants.ConstantMessage.MIN_PASSWORD_LENGTH;

@Data
@AllArgsConstructor
public class RegisterInDTO {

    @Email
    @NotBlank(message = "Valid Email not found")
    @Pattern(
            regexp = "^[\\w.%+-]+@(gmail|nucleusteq)\\.com$",
            message = "Valid Email not found"
    )
    private String email;

    @Pattern(
            regexp = "^[a-zA-Z\\s'-]{2,50}$",
            message = "A valid name is mandatory"
    )
    @NotBlank(message = "A valid name is mandatory")
    private String name;

    @NotBlank(message = "Password should be minimum 8 characters")
    @Size(min = MIN_PASSWORD_LENGTH, message = "Password should be minimum 8 characters")
    private String password;

    @NotNull(message = "Role is mandatory")
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterInDTO that = (RegisterInDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password, role);
    }
}
