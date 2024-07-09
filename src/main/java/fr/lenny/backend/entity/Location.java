package fr.lenny.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int surface;

    private float price;

    private String picture;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    private Date createdAt;

    private Date updatedAt;
}
