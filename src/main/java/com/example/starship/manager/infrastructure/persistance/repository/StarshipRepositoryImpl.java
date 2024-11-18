package com.example.starship.manager.infrastructure.persistance.repository;

import com.example.starship.manager.application.mapper.StarshipMapper;
import com.example.starship.manager.domain.model.Starship;
import com.example.starship.manager.domain.port.StarshipRepository;
import com.example.starship.manager.infrastructure.persistance.entity.StarshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StarshipRepositoryImpl implements StarshipRepository {

    private final JpaStarshipRepository jpaStarshipRepository;
    private final StarshipMapper starshipMapper;

    @Autowired
    public StarshipRepositoryImpl(JpaStarshipRepository jpaStarshipRepository, StarshipMapper starshipMapper) {
        this.jpaStarshipRepository = jpaStarshipRepository;
        this.starshipMapper = starshipMapper;
    }

    @Override
    public Optional<Starship> findById(Long id) {
        return jpaStarshipRepository.findById(id)
                .map(starshipMapper::toDomainModelFromEntity);
    }

    @Override
    public List<Starship> findAll(Pageable page) {
        return jpaStarshipRepository.findAll(page).stream()
                .map(starshipMapper::toDomainModelFromEntity)
                .toList();
    }

    @Override
    public List<Starship> findByNameContaining(String name) {
        return jpaStarshipRepository.findByNameContaining(name).stream()
                .map(starshipMapper::toDomainModelFromEntity)
                .toList();
    }

    @Override
    public Starship save(Starship starship) {
        StarshipEntity entity = starshipMapper.toEntityFromDomain(starship);
        StarshipEntity savedEntity = jpaStarshipRepository.save(entity);
        return starshipMapper.toDomainModelFromEntity(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaStarshipRepository.deleteById(id);
    }

}
