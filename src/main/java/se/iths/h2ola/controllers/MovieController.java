package se.iths.h2ola.controllers;


import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.dtos.MovieGenre;
import se.iths.h2ola.services.Service;

import java.util.List;


@RestController
public class MovieController {

    private final Service service;

    public MovieController(Service service)
    {
        this.service = service;
    }

    @GetMapping("/movies")
    public List<MovieDto> all()
    {
        return service.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public MovieDto one(@PathVariable Long id) {
        return service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found."));
    }

    @GetMapping("/movies/search/{title}")
    public List<MovieDto> title(@PathVariable String title) {
        return service.getAllByTitle(title);
    }

    //    Below is an alternate search with search?=title instead
//    @GetMapping("/movies/search")
//    public List<MovieDto> title(@RequestParam String title) {
//        return service.getAllByTitle(title);
//    }

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto create(@RequestBody MovieDto movie) {
        return service.createMovie(movie);
    }


    @DeleteMapping("/movies/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/movies/{id}")
    public MovieDto replace(@RequestBody MovieDto movieDto, @PathVariable Long id) {
        return service.replace(id, movieDto);
    }

    @PatchMapping("/movies/{id}")
    public MovieDto update(@RequestBody MovieGenre movieGenre, @PathVariable Long id) {
        return service.update(id, movieGenre);
    }
}
