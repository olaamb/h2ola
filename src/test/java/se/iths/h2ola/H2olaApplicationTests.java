package se.iths.h2ola;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import se.iths.h2ola.dtos.MovieDto;
import se.iths.h2ola.services.Service;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class H2olaApplicationTests {

	@LocalServerPort
	int port;

	@Autowired
	TestRestTemplate testClient;


	@Test
	void contextLoads() {

		MovieDto movieDto = new MovieDto(1L, "test", "test");
		MovieDto movieDto2 = new MovieDto(2L, "test2", "test2");
		MovieDto movieDto3 = new MovieDto(3L, "test3", "test3");

		//createMovie(POST)
		var result = testClient.postForEntity("http://localhost:" + port + "/movies", movieDto, MovieDto.class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		var result2 = testClient.postForEntity("http://localhost:" + port + "/movies", movieDto2, MovieDto.class);
		assertThat(result2.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		var result3 = testClient.postForEntity("http://localhost:" + port + "/movies", movieDto3, MovieDto.class);
		assertThat(result3.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		//getAll(GET)
		var result4 = testClient.getForEntity("http://localhost:" + port + "/movies/", MovieDto[].class);
		assertThat(result4.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result4.getBody().length).isGreaterThan(0);

		//getOne(GET)
		var result5 = testClient.getForEntity("http://localhost:" + port + "/movies/1", MovieDto.class);
		assertThat(result5.getStatusCode()).isEqualTo(HttpStatus.OK);

		//getByTitle(GET)
		var result6 = testClient.getForEntity("http://localhost:" + port + "/movies/search/test", MovieDto[].class);
		assertThat(result6.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result6.getBody().length).isGreaterThan(0);


		//replace(PUT) tests in MvcTest

		//update(PATCH) tests in MvcTest

	}
}



