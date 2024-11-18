package com.example.starship.manager.domain.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Film {

    private Long id;

    private String title;

    private String director;

    private String releaseDate;

    private Set<Starship> starships = new HashSet<>();
}
