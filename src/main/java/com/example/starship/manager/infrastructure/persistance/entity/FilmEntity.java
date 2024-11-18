package com.example.starship.manager.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String director;

    private String releaseDate;

    @ManyToMany(mappedBy = "films")
    private Set<StarshipEntity> starships = new HashSet<>();
}