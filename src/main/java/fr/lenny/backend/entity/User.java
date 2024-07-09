package fr.lenny.backend.entity;

import jakarta.persistence.*;
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
    private int id;

    private String name;

    private String email;

    private String password;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Location> locations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;
}
