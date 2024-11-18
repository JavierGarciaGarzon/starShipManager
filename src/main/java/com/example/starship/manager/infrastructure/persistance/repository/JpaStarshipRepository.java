package com.example.starship.manager.infrastructure.persistance.repository;


import com.example.starship.manager.infrastructure.persistance.entity.StarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStarshipRepository extends JpaRepository<StarshipEntity, Long> {

    List<StarshipEntity> findByNameContaining(String name);
}
