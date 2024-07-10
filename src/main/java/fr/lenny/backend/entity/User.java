package fr.lenny.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Long;

    @NotBlank(message = "Name may not be empty")
    private String name;

    @Email
    private String email;

    @NotBlank(message = "Password may not be empty")
    @Size(min = 5, message = "Password too short")
    private String password;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Location> locations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;
}
