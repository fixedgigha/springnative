package com.example.jimnative;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class JimnativeApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void getExistingEmployee() {
		webTestClient.get()
				.uri("/employees/1")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody().jsonPath("name").isEqualTo("reactive one");
	}

}
