package com.example.starship.manager.application.service;

import com.example.starship.manager.application.dto.StarshipDto;
import com.example.starship.manager.application.mapper.StarshipMapper;
import com.example.starship.manager.domain.exception.ResourceNotFoundException;
import com.example.starship.manager.domain.model.Starship;
import com.example.starship.manager.domain.port.StarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarshipService {

    private final StarshipRepository starshipRepository;
    private final StarshipMapper starshipMapper;

    @Autowired
    public StarshipService(StarshipRepository starshipRepository, StarshipMapper starshipMapper) {
        this.starshipRepository = starshipRepository;
        this.starshipMapper = starshipMapper;
    }

    @Cacheable(value = "starships", key = "'allStarships_' + #page + '_' + #size")
    public List<StarshipDto> getAllStarships(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Starship> starships = starshipRepository.findAll(pageable);
        return starshipMapper.toDtoList(starships);
    }

    @Cacheable(value = "starships", key = "#id")  // Caché por ID
    public StarshipDto getStarshipById(Long id) {
        Optional<Starship> starship = starshipRepository.findById(id);
        return starship.map(starshipMapper::modelToDto).orElseThrow(() -> new ResourceNotFoundException("Starship not found with ID: " + id));
    }

    public StarshipDto createStarship(StarshipDto starshipDto) {
        Starship starship = starshipMapper.toDomainModel(starshipDto);
        Starship createdStarship = starshipRepository.save(starship);
        return starshipMapper.modelToDto(createdStarship);
    }

    public StarshipDto updateStarship(Long id, StarshipDto starshipDto) {
        Optional<Starship> existingStarship = starshipRepository.findById(id);
        if (existingStarship.isEmpty()) {
            throw new ResourceNotFoundException("Starship not found with ID: " + id);
        }
        starshipDto.setId(id);
        Starship starship = starshipMapper.toDomainModel(starshipDto);
        Starship updatedStarship = starshipRepository.save(starship);
        return starshipMapper.modelToDto(updatedStarship);
    }

    @CacheEvict(value = "starships", key = "#id")
    public void deleteStarshipById(Long id) {
        if (starshipRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Cannot delete. Starship not found with ID: " + id);
        }
        starshipRepository.deleteById(id);
    }


    public List<StarshipDto> searchStarshipsByName(String name) {
        List<Starship> starships = starshipRepository.findByNameContaining(name);
        return starshipMapper.toDtoList(starships);
    }
}


