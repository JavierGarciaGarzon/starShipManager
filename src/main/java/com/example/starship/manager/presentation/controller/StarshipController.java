package com.example.starship.manager.presentation.controller;

import com.example.starship.manager.application.dto.StarshipDto;
import com.example.starship.manager.application.service.StarshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starships")
public class StarshipController {

    private final StarshipService starshipService;


    public StarshipController(StarshipService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping
    public ResponseEntity<List<StarshipDto>> getAllStarships(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        List<StarshipDto> starships = starshipService.getAllStarships(page, size);
        return ResponseEntity.ok(starships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarshipDto> getStarshipById(@PathVariable Long id) {
        StarshipDto starship = starshipService.getStarshipById(id);
        return ResponseEntity.ok(starship);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StarshipDto>> searchStarshipsByName(@RequestParam String name) {
        List<StarshipDto> starships = starshipService.searchStarshipsByName(name);
        return ResponseEntity.ok(starships);
    }

    @PostMapping
    public ResponseEntity<StarshipDto> createStarship(@RequestBody StarshipDto starshipDTO) {
        StarshipDto createdStarship = starshipService.createStarship(starshipDTO);
        return new ResponseEntity<>(createdStarship, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarshipDto> updateStarship(@PathVariable Long id, @RequestBody StarshipDto starshipDto) {
        StarshipDto updatedStarship = starshipService.updateStarship(id, starshipDto);
        return ResponseEntity.ok(updatedStarship);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteStarship(@PathVariable Long id) {
        starshipService.deleteStarshipById(id);
        return ResponseEntity.noContent().build();
    }
}
