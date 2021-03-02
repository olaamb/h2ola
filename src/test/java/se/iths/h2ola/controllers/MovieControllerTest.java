package se.iths.h2ola.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieControllerTest {

    @Test
    void callingOneWithValidIdReturnsOneMovie(){
        MovieController personController = new MovieController(new TestService());

        var person = personController.one(1L);

        assertThat(person.getId()).isEqualTo(1);
        assertThat(person.getTitle()).isEqualTo("Test");
        assertThat(person.getGenre()).isEqualTo("Test");
    }

    @Test
    void callingOneWithInvalidIdThrowsExceptionWithResponseStatus404(){
        MovieController movieController = new MovieController(new TestService());

        var exception = assertThrows(ResponseStatusException.class,
                () -> movieController.one(2L) );
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }



}