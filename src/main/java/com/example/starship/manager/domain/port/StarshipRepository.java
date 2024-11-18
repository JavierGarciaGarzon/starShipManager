package com.example.starship.manager.domain.port;


import com.example.starship.manager.domain.model.Starship;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StarshipRepository {
    Optional<Starship> findById(Long id);

    List<Starship> findAll(Pageable page);

    List<Starship> findByNameContaining(String name);

    Starship save(Starship starship);

    void deleteById(Long id);
}
