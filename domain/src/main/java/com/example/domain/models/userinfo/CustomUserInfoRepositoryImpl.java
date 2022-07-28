package com.example.domain.models.userinfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CustomUserInfoRepositoryImpl extends QuerydslRepositorySupport implements CustomUserInfoRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public CustomUserInfoRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
		super(UserInfo.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}

}
