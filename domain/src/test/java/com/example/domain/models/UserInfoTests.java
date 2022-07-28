package com.example.domain.models;

import com.example.domain.config.JPAConfig;
import com.example.domain.models.userinfo.UserInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@EnableAutoConfiguration
@ContextConfiguration(
		classes = {UserInfoRepository.class, JPAConfig.class}
)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserInfoTests {

	@Autowired
	private UserInfoRepository repository;

	@DisplayName("기본 findAll")
	@Test
	void findAll() {
		repository.findAll();
	}

}
