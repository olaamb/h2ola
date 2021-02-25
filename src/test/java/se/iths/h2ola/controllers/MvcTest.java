package se.iths.h2ola.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.services.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(MovieController.class)
//@Import(TestService.class)
public class MvcTest {

  //  @Autowired
    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingWithUrlPersonsShouldReturnAllPersonsAsJson() throws Exception {
        when(service.getAllMovies()).thenReturn(List.of(new MovieDto(1,"","")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }
}
