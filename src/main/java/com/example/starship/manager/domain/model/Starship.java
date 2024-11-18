package com.example.starship.manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Starship {

    private Long id;
    private String name;
    private int maxAtmospheringSpeed;
    private Set<Film> films;  // Relación muchos a muchos con Film
}