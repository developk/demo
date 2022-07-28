package com.example.domain.models;

import com.example.domain.config.JPAConfig;
import com.example.domain.models.datatyps.DataTypesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

@EnableAutoConfiguration
@ContextConfiguration(
		classes = {DataTypesRepository.class, JPAConfig.class}
)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataTypesTests {

	@Autowired
	private DataTypesRepository dataTypesRepository;

	@DisplayName("Expressions.dateTemplate")
	@Test
	void dateRangeTest() {
		dataTypesRepository.findWithDateRange(LocalDate.now(), LocalDate.now());
	}

	@DisplayName("Expressions.dateTemplate For H2")
	@Test
	void dateRangeForH2Test() {
		dataTypesRepository.findWithDateRangeForH2(LocalDate.now(), LocalDate.now());
	}

}
