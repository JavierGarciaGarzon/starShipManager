package com.example.starship.manager.application.service;

import com.example.starship.manager.application.dto.StarshipDto;
import com.example.starship.manager.application.mapper.StarshipMapper;
import com.example.starship.manager.domain.exception.ResourceNotFoundException;
import com.example.starship.manager.domain.model.Starship;
import com.example.starship.manager.domain.port.StarshipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StarshipServiceTest {

    @Mock
    private StarshipRepository starshipRepository;

    @Mock
    private StarshipMapper starshipMapper;

    @InjectMocks
    private StarshipService starshipService;

    public StarshipServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStarshipById_ExistingId_ReturnsStarshipDto() {
        Long starshipId = 1L;
        Starship starship = new Starship();
        starship.setId(starshipId);
        starship.setName("Millennium Falcon");

        StarshipDto starshipDto = new StarshipDto();
        starshipDto.setId(starshipId);
        starshipDto.setName("Millennium Falcon");

        when(starshipRepository.findById(starshipId)).thenReturn(Optional.of(starship));
        when(starshipMapper.modelToDto(starship)).thenReturn(starshipDto);

        StarshipDto result = starshipService.getStarshipById(starshipId);

        assertNotNull(result);
        assertEquals(starshipId, result.getId());
        assertEquals("Millennium Falcon", result.getName());
        verify(starshipRepository, times(1)).findById(starshipId);
        verify(starshipMapper, times(1)).modelToDto(starship);
    }

    @Test
    void getStarshipById_NonExistingId_ThrowsResourceNotFoundException() {
        Long starshipId = 2L;
        when(starshipRepository.findById(starshipId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            starshipService.getStarshipById(starshipId);
        });

        assertEquals("Starship not found with ID: " + starshipId, exception.getMessage());
        verify(starshipRepository, times(1)).findById(starshipId);
        verifyNoInteractions(starshipMapper);
    }

    @Test
    void getAllStarships_ReturnsListOfStarshipDto() {
        Starship starship1 = new Starship();
        starship1.setId(1L);
        starship1.setName("Millennium Falcon");

        Starship starship2 = new Starship();
        starship2.setId(2L);
        starship2.setName("X-Wing");

        List<Starship> starships = List.of(starship1, starship2);

        StarshipDto dto1 = new StarshipDto();
        dto1.setId(1L);
        dto1.setName("Millennium Falcon");

        StarshipDto dto2 = new StarshipDto();
        dto2.setId(2L);
        dto2.setName("X-Wing");

        List<StarshipDto> starshipDtos = List.of(dto1, dto2);
        Pageable page = PageRequest.of(0, 10);

        when(starshipRepository.findAll(page)).thenReturn(starships);
        when(starshipMapper.toDtoList(starships)).thenReturn(starshipDtos);

        List<StarshipDto> result = starshipService.getAllStarships(page.getPageNumber(), page.getPageSize());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Millennium Falcon", result.get(0).getName());
        assertEquals("X-Wing", result.get(1).getName());
        verify(starshipRepository, times(1)).findAll(page);
        verify(starshipMapper, times(1)).toDtoList(starships);
    }

    @Test
    void createStarship_ReturnsCreatedStarshipDto() {
        StarshipDto inputDto = new StarshipDto();
        inputDto.setName("TIE Fighter");

        Starship starship = new Starship();
        starship.setName("TIE Fighter");

        Starship savedStarship = new Starship();
        savedStarship.setId(1L);
        savedStarship.setName("TIE Fighter");

        StarshipDto outputDto = new StarshipDto();
        outputDto.setId(1L);
        outputDto.setName("TIE Fighter");

        when(starshipMapper.toDomainModel(inputDto)).thenReturn(starship);
        when(starshipRepository.save(starship)).thenReturn(savedStarship);
        when(starshipMapper.modelToDto(savedStarship)).thenReturn(outputDto);

        StarshipDto result = starshipService.createStarship(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("TIE Fighter", result.getName());
        verify(starshipMapper, times(1)).toDomainModel(inputDto);
        verify(starshipRepository, times(1)).save(starship);
        verify(starshipMapper, times(1)).modelToDto(savedStarship);
    }

    @Test
    void updateStarship_ExistingId_ReturnsUpdatedStarshipDto() {
        Long starshipId = 1L;
        StarshipDto inputDto = new StarshipDto();
        inputDto.setName("Updated Starship");

        Starship existingStarship = new Starship();
        existingStarship.setId(starshipId);
        existingStarship.setName("Old Starship");

        Starship updatedStarship = new Starship();
        updatedStarship.setId(starshipId);
        updatedStarship.setName("Updated Starship");

        StarshipDto outputDto = new StarshipDto();
        outputDto.setId(starshipId);
        outputDto.setName("Updated Starship");

        when(starshipRepository.findById(starshipId)).thenReturn(Optional.of(existingStarship));
        when(starshipMapper.toDomainModel(inputDto)).thenReturn(updatedStarship);
        when(starshipRepository.save(updatedStarship)).thenReturn(updatedStarship);
        when(starshipMapper.modelToDto(updatedStarship)).thenReturn(outputDto);

        StarshipDto result = starshipService.updateStarship(starshipId, inputDto);

        assertNotNull(result);
        assertEquals(starshipId, result.getId());
        assertEquals("Updated Starship", result.getName());
        verify(starshipRepository, times(1)).findById(starshipId);
        verify(starshipRepository, times(1)).save(updatedStarship);
        verify(starshipMapper, times(1)).toDomainModel(inputDto);
        verify(starshipMapper, times(1)).modelToDto(updatedStarship);
    }

    @Test
    void deleteStarshipById_ExistingId_DeletesStarship() {
        Long starshipId = 1L;

        Starship starship = new Starship();
        starship.setId(starshipId);

        when(starshipRepository.findById(starshipId)).thenReturn(Optional.of(starship));

        starshipService.deleteStarshipById(starshipId);

        verify(starshipRepository, times(1)).findById(starshipId);
        verify(starshipRepository, times(1)).deleteById(starshipId);
    }

    @Test
    void searchStarshipsByName_ReturnsMatchingStarshipDtos() {
        String name = "Star";
        Starship starship1 = new Starship();
        starship1.setId(1L);
        starship1.setName("Star Destroyer");

        Starship starship2 = new Starship();
        starship2.setId(2L);
        starship2.setName("Starfighter");

        List<Starship> starships = List.of(starship1, starship2);

        StarshipDto dto1 = new StarshipDto();
        dto1.setId(1L);
        dto1.setName("Star Destroyer");

        StarshipDto dto2 = new StarshipDto();
        dto2.setId(2L);
        dto2.setName("Starfighter");

        when(starshipRepository.findByNameContaining(name)).thenReturn(starships);
        when(starshipMapper.toDtoList(starships)).thenReturn(List.of(dto1, dto2));

        List<StarshipDto> result = starshipService.searchStarshipsByName(name);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Star Destroyer", result.get(0).getName());
        assertEquals("Starfighter", result.get(1).getName());
        verify(starshipRepository, times(1)).findByNameContaining(name);
        verify(starshipMapper, times(1)).toDtoList(starships);
    }

}
