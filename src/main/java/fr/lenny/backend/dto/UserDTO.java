package fr.lenny.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name may not be empty")
    private String name;

    @Email
    private String email;

    @NotBlank(message = "Password may not be empty")
    @Size(min = 5, message = "Password too short")
    private String password;

    private Date createdAt;

    private Date updatedAt;
}
