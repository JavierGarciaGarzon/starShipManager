package com.example.starship.manager.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "starships")
@Getter
@Setter
public class StarshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "max_atmosphering_speed")
    private int maxAtmospheringSpeed;


    @ManyToMany
    @JoinTable(
            name = "starship_films",
            joinColumns = @JoinColumn(name = "starship_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private Set<FilmEntity> films = new HashSet<>();
}
