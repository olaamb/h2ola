package se.iths.h2ola.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.dtos.MovieGenre;
import se.iths.h2ola.entities.Movie;

import se.iths.h2ola.repositories.MovieRepository;
import se.iths.h2ola.mappers.MovieMapper;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements se.iths.h2ola.services.Service {

    private final MovieMapper movieMapper;
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAllMovies()
    {
        return movieMapper.mapp(movieRepository.findAll());
    }

    @Override
    public Optional<MovieDto> getOne(Long id)
    {
        return movieMapper.mapp(movieRepository.findById(id));
    }

    @Override
    public List<MovieDto> getAllByTitle(String title)
    {
        return movieMapper.mapp(movieRepository.findAllByTitle(title));
    }

    @Override
    public MovieDto createMovie(MovieDto movie) {
        if (movie.getTitle().isEmpty())
            throw new RuntimeException();
        return movieMapper.mapp(movieRepository.save(movieMapper.mapp(movie)));
    }

    @Override
    public void delete(Long id)
    {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDto replace(Long id, MovieDto movieDto) {
        Optional<Movie> movie = movieRepository.findById(id);
        if( movie.isPresent())
        {
            Movie updatedMovie = movie.get();
            updatedMovie.setTitle(movieDto.getTitle());
            updatedMovie.setGenre(movieDto.getGenre());
            return movieMapper.mapp(movieRepository.save(updatedMovie));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    @Override
    public MovieDto update(Long id, MovieGenre movieGenre) {
        Optional<Movie> movie = movieRepository.findById(id);
        if( movie.isPresent())
        {
            Movie updatedMovie = movie.get();
            if( movieGenre.genre != null)
                updatedMovie.setGenre(movieGenre.genre);
            return movieMapper.mapp(movieRepository.save(updatedMovie));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }
}
