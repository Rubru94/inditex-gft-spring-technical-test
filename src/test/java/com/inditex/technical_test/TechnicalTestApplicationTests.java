package com.inditex.technical_test;

import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechnicalTestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMain() {
		try (var mock = mockStatic(SpringApplication.class)) {
			TechnicalTestApplication.main(new String[] {});
			mock.verify(() -> SpringApplication.run(TechnicalTestApplication.class, new String[] {}));
		}
	}
}
