package com.example.starship.manager.application.mapper;

import com.example.starship.manager.domain.model.Film;
import com.example.starship.manager.infrastructure.persistance.entity.FilmEntity;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    Film toDomain(FilmEntity filmEntity);

    FilmEntity toEntity(Film film);

    default Set<Long> mapFilmsEntityToIds(Set<FilmEntity> films) {
        return films.stream().map(FilmEntity::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapFilmsToIds(Set<Film> films) {
        return films.stream().map(Film::getId).collect(Collectors.toSet());
    }

    default Set<FilmEntity> mapIdsToFilmsEntity(Set<Long> filmIds) {
        return filmIds.stream().map(id -> {
            FilmEntity film = new FilmEntity();
            film.setId(id);
            return film;
        }).collect(Collectors.toSet());
    }

    default Set<Film> mapIdsToFilms(Set<Long> filmIds) {
        return filmIds.stream().map(id -> {
            Film film = new Film();
            film.setId(id);
            return film;
        }).collect(Collectors.toSet());
    }
}
