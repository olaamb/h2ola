package se.iths.h2ola.controllers;

import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.dtos.MovieGenre;
import se.iths.h2ola.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {
    @Override
    public List<MovieDto> getAllPersons() {
        return List.of(new MovieDto(1,"Test","Test"), new MovieDto(2,"Test2","Test2"));
    }

    @Override
    public Optional<MovieDto> getOne(Long id) {
        if( id == 1)
            return Optional.of(new MovieDto(1,"Test","Test"));
        return Optional.empty();
    }

    @Override
    public MovieDto createPerson(MovieDto person) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MovieDto replace(Long id, MovieDto movieDto) {
        return null;
    }

    @Override
    public MovieDto update(Long id, MovieGenre movieGenre) {
        return null;
    }
}
