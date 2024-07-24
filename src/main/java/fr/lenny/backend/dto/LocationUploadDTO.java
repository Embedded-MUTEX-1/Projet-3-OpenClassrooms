package fr.lenny.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationUploadDTO {
    private Long id;

    @NotBlank(message = "Name may not be empty")
    @Size(min = 5, message = "Name too short")
    private String name;

    @NotNull
    private Integer surface;

    @NotNull
    private Float price;

    private MultipartFile picture;

    @NotBlank(message = "Description may not be empty")
    @Size(min = 10, message = "Description too short")
    private String description;

    private Date created_at;

    private Date updated_at;
}
