package com.example.domain.models.datatyps;

import com.example.domain.models.commons.CommonTemplates;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;


public class DataTypesPredicates {

	public static BooleanExpression toDate(QDataTypes dataTypes, LocalDate from) {
		return CommonTemplates.toDateGeneral(dataTypes.dateTimeVal)
						.eq(from);
	}

	public static BooleanExpression toDateForH2(QDataTypes dataTypes, LocalDate from) {
		return CommonTemplates.toDateForH2(dataTypes.dateTimeVal)
						.eq(from);
	}

}
