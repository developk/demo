package com.example.domain.models.datatyps;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

import static com.example.domain.models.datatyps.QDataTypes.dataTypes;

public class CustomDataTypesRepositoryImpl extends QuerydslRepositorySupport implements CustomDataTypesRepository {

	private final JPAQueryFactory jpaQueryFactory;

	private final JPASQLQuery<DataTypes> jpaSqlQuery;

	public CustomDataTypesRepositoryImpl(
			final JPAQueryFactory jpaQueryFactory,
			final JPASQLQuery<DataTypes> jpaSqlQuery) {

		super(DataTypes.class);

		this.jpaQueryFactory = jpaQueryFactory;
		this.jpaSqlQuery = jpaSqlQuery;
	}

	@Override
	public List<DataTypes> findWithDateRange(LocalDate from, LocalDate to) {

		return jpaSqlQuery
				       .select(dataTypes)
				       .from(dataTypes)
				       .where(DataTypesPredicates.toDate(dataTypes, from))
				       .fetch();
	}

	@Override
	public List<DataTypes> findWithDateRangeForH2(LocalDate from, LocalDate to) {
		return jpaQueryFactory
				       .selectFrom(dataTypes)
				       .from(dataTypes)
				       .where(DataTypesPredicates.toDateForH2(dataTypes, from))
				       .fetch();
	}
}
