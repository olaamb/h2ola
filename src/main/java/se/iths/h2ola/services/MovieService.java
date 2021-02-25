package se.iths.h2ola.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.dtos.MovieGenre;
import se.iths.h2ola.entities.Movie;
import se.iths.h2ola.mappers.MovieMapper;
import se.iths.h2ola.repositories.MovieRepsitory;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements se.iths.h2ola.services.Service {

    private final MovieMapper movieMapper;
    private MovieRepsitory movieRepsitory;

    public MovieService(MovieRepsitory movieRepsitory, MovieMapper movieMapper) {
        this.movieRepsitory = movieRepsitory;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieMapper.mapp(movieRepsitory.findAll());
    }

    @Override
    public Optional<MovieDto> getOne(Long id) {
        return movieMapper.mapp(movieRepsitory.findById(id));
    }

    @Override
    public MovieDto createMovie(MovieDto person) {
        if (person.getTitle().isEmpty())
            throw new RuntimeException();

        //Mapp from MovieDto to Movie
        return movieMapper.mapp(movieRepsitory.save(movieMapper.mapp(person)));
    }

    @Override
    public void delete(Long id) {
        movieRepsitory.deleteById(id);
    }

    @Override
    public MovieDto replace(Long id, MovieDto movieDto) {
        Optional<Movie> person = movieRepsitory.findById(id);
        if( person.isPresent())
        {
            Movie updatedMovie = person.get();
            updatedMovie.setTitle(movieDto.getTitle());
            updatedMovie.setGenre(movieDto.getGenre());
            return movieMapper.mapp(movieRepsitory.save(updatedMovie));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    @Override
    public MovieDto update(Long id, MovieGenre movieGenre) {
        Optional<Movie> movie = movieRepsitory.findById(id);
        if( movie.isPresent())
        {
            Movie updatedMovie = movie.get();
            if( movieGenre.email != null)
                updatedMovie.setGenre(movieGenre.email);
            return movieMapper.mapp(movieRepsitory.save(updatedMovie));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }
}
