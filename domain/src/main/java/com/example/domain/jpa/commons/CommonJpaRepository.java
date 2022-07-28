package com.example.domain.jpa.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonJpaRepository<Entity, ID> extends JpaRepository<Entity, ID>, JpaSpecificationExecutor<Entity> {
}
