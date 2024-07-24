package fr.lenny.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @Email
    private String email;

    @NotBlank(message = "Password may not be empty")
    @Size(min = 5, message = "Password too short")
    private String password;
}
