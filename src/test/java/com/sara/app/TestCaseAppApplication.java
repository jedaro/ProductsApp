package com.sara.app;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestCaseAppApplication {

	@Autowired
	WebTestClient webTestClient;

	@Test
	@DisplayName("TestCase 1 Get Products ok")
	@Order(1)
	public void getProductSimilarOk(){

		// given

		// when
		webTestClient.get().uri("/product/"+1+"/similar").exchange()

				// then
				.expectStatus().isOk();
	}

	@Test
	@DisplayName("TestCase 2 Get Products not found")
	@Order(2)
	public void getProductSimilarNotFound(){

		// given

		// when
		webTestClient.get().uri("/product/"+8+"/similar").exchange()

				// then
				.expectStatus().isNotFound();
	}


}
