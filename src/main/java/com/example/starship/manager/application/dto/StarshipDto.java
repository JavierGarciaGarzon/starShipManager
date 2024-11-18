package com.example.starship.manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarshipDto {

    private Long id;
    private String name;
    private int maxAtmospheringSpeed;
    private Set<Long> filmIds;  // Almacena los IDs de las películas en las que aparece esta nave

}

