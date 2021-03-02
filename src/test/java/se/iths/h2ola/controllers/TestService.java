package se.iths.h2ola.controllers;

import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.dtos.MovieGenre;
import se.iths.h2ola.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {

    @Override
    public List<MovieDto> getAllMovies() {
        return List.of(new MovieDto(1,"MovieTest1","MovieTest1"), new MovieDto(2,"MovieTest2","MovieTest2"));
    }
    @Override
    public Optional<MovieDto> getOne(Long id) {
        if( id == 1)
            return Optional.of(new MovieDto(1,"TestMovie","TestMovie"));
        return Optional.empty();
    }
    @Override
    public List<MovieDto> getAllByTitle(String title) {
        return List.of(new MovieDto(1,"MovieTest1","MovieTest1"), new MovieDto(2,"MovieTest2","MovieTest2"));
    }

    @Override
    public MovieDto createMovie(MovieDto movie) {
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
