package se.iths.h2ola.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.entities.Movie;
import se.iths.h2ola.services.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.contains;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;


@WebMvcTest(MovieController.class)
//@Import(TestService.class)
public class MvcTest {

  //  @Autowired
    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingWithUrlMoviesShouldReturnAllMoviesAsJson() throws Exception {
        when(service.getAllMovies()).thenReturn(List.of(new MovieDto(1,"","")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }

    @Test
    void callingWithUrlMoviesSlashIdShouldReturnMovieWithThatIdAsJson() throws Exception {
        when(service.getOne(1L)).thenReturn(Optional.of(new MovieDto(1L, "", "")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void callingWithUrlMoviesSlashSearchSlashTitleShouldReturnAllMoviesWithThatTitleAsJson() throws Exception {
        when(service.getAllByTitle("test")).thenReturn(List.of(new MovieDto(1,"test","test")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/movies/search/test")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }

    @Test
    void callingWithPostMappingUrlMoviesShouldReturnCreatedMovieAsJson() throws Exception {
        when(service.createMovie(any(MovieDto.class))).thenReturn(new MovieDto(1L, "", ""));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/movies")
                .content(asJsonString(new MovieDto(1L, "Test2", "Test2")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void callingWithDeleteMappingUrlMoviesSlashIdShouldDeleteMovie() throws Exception {
        service.delete(1L);
        verify(service).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/movies/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void callingWithPutMappingUrlMoviesSlashIdAndSendAsJsonShouldPutMovie() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .put("/movies/{id}", 2L)
                .content(asJsonString(new MovieDto(2L, "Test2", "Test")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk());
    }

    @Test
    void callingWithPatchMappingUrlMoviesSlashIdAndSendAsJsonShouldPatchMovie() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/movies/{id}", 2L)
                .content(asJsonString(new MovieDto(2L, "Test2", "Test")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final MovieDto movieDto) {
        try {
            return new ObjectMapper().writeValueAsString(movieDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
