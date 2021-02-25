package se.iths.h2ola.services;

import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.dtos.MovieGenre;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<MovieDto> getAllMovies();

    Optional<MovieDto> getOne(Long id);

    MovieDto createMovie(MovieDto person);

    void delete(Long id);

    MovieDto replace(Long id, MovieDto movieDto);

    MovieDto update(Long id, MovieGenre movieGenre);
}
