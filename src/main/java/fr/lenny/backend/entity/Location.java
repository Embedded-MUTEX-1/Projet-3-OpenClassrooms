package fr.lenny.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Long;

    @NotBlank(message = "Name may not be empty")
    private String name;

    @NotNull
    private Integer surface;

    @NotNull
    private Float price;

    private String picture;

    @NotBlank(message = "Description may not be empty")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    private Date createdAt;

    private Date updatedAt;
}
