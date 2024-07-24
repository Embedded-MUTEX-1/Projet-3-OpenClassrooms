package fr.lenny.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;

    @NotBlank(message = "Message may not be empty")
    @Size(min = 5, message = "Message too short")
    private String message;

    private long rental_id;

    private long user_id;
}
