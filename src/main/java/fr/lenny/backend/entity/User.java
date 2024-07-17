package fr.lenny.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    private String password;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Location> locations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;
}
