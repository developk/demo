package com.example.domain.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EntityScan(basePackages = "com.example.domain")
@EnableJpaRepositories(basePackages = "com.example.domain")
@EnableJpaAuditing
@Configuration
@Slf4j
public class JPAConfig {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}

	@Bean
	public JPASQLQuery<?> jpaSQLQuery() {
		SQLTemplates templates = getDetectedSqlTemplates();
		return new JPASQLQuery<Void>(entityManager, templates);
	}


	private SQLTemplates getDetectedSqlTemplates() {
		Session session = entityManager.unwrap(Session.class);
		SessionFactory sessionFactory = session.getSessionFactory();
		Dialect dialect = ((SessionFactoryImplementor) sessionFactory).getJdbcServices().getDialect();

		return switch (dialect.toString()) {
			case "org.hibernate.dialect.H2Dialect" -> H2Templates.builder().build();
			case "org.hibernate.dialect.MySQL8Dialect", "org.hibernate.dialect.MySQL55Dialect", "org.hibernate.dialect.MySQL57Dialect", "org.hibernate.dialect.MySQLDialect", "org.hibernate.dialect.MariaDB10Dialect", "org.hibernate.dialect.MariaDB53Dialect", "org.hibernate.dialect.MariaDB102Dialect", "org.hibernate.dialect.MariaDB103Dialect", "org.hibernate.dialect.MariaDB106Dialect", "org.hibernate.dialect.MariaDBDialect" ->
					new MySQLTemplates();
			case "org.hibernate.dialect.Oracle8iDialect", "org.hibernate.dialect.Oracle9iDialect", "org.hibernate.dialect.Oracle10gDialect", "org.hibernate.dialect.Oracle12cDialect" ->
					new OracleTemplates();
			case "org.hibernate.dialect.PostgresPlusDialect", "org.hibernate.dialect.PostgreSQL9Dialect", "org.hibernate.dialect.PostgreSQL10Dialect", "org.hibernate.dialect.PostgreSQL81Dialect", "org.hibernate.dialect.PostgreSQL82Dialect", "org.hibernate.dialect.PostgreSQL91Dialect", "org.hibernate.dialect.PostgreSQL92Dialect", "org.hibernate.dialect.PostgreSQL93Dialect", "org.hibernate.dialect.PostgreSQL94Dialect", "org.hibernate.dialect.PostgreSQL95Dialect", "org.hibernate.dialect.ProgressDialect" ->
					new PostgreSQLTemplates();
			default -> new H2Templates();
		};
	}

}
