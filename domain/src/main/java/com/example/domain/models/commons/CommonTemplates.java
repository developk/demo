package com.example.domain.models.commons;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.DateOperation;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommonTemplates {

	public static DateOperation<LocalDate> toDateGeneral(DateTimePath<LocalDateTime> dateTimePath) {
		return Expressions.dateOperation(
				LocalDate.class,
				Ops.DateTimeOps.DATE,
				dateTimePath
		);
	}

	public static DateTemplate<LocalDate> toDateForH2(DateTimePath<LocalDateTime> dateTimePath) {
		return Expressions
				       .dateTemplate(LocalDate.class, "CONVERT({0}, DATE)", dateTimePath);
	}

}
