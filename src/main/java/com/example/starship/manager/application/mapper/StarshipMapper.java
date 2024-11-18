package com.example.starship.manager.application.mapper;

import com.example.starship.manager.application.dto.StarshipDto;
import com.example.starship.manager.domain.model.Starship;
import com.example.starship.manager.infrastructure.persistance.entity.StarshipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FilmMapper.class})
public interface StarshipMapper {
    @Mapping(source = "films", target = "filmIds")
    StarshipDto toDto(StarshipEntity starshipEntity);

    @Mapping(source = "filmIds", target = "films")
    StarshipEntity toEntity(StarshipDto starshipDTO);

    @Mapping(source = "films", target = "filmIds")
    StarshipDto modelToDto(Starship starship);

    @Mapping(source = "filmIds", target = "films")
    Starship toDomainModel(StarshipDto starshipDto);

    // Mapeo de lista de entidades Starship a lista de DTOs StarshipDto
    List<StarshipDto> toDtoList(List<Starship> starships);

    // Mapea StarshipEntity a Starship (modelo de dominio)
    @Mapping(source = "films", target = "films")
    Starship toDomainModelFromEntity(StarshipEntity starshipEntity);

    // Mapea Starship (modelo de dominio) a StarshipEntity
    @Mapping(source = "films", target = "films")
    StarshipEntity toEntityFromDomain(Starship starship);

}
