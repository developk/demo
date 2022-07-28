package com.example.domain.helper;

import com.querydsl.core.types.EntityPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.RelationalPathBase;

import javax.persistence.Table;
import java.lang.reflect.AnnotatedElement;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class QueryDSLHelper {

	private static final ConcurrentMap<EntityPath<?>, RelationalPath<?>> relationalMap = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> RelationalPath<T> asRelational(EntityPath<T> entityPath) {

		AnnotatedElement annotatedElement = Objects.requireNonNull(Objects.requireNonNull(entityPath, "entityPath is null").getAnnotatedElement(), "no annotation");

		Table table = Objects.requireNonNull(annotatedElement.getAnnotation(Table.class), "no entity table");

		RelationalPath<?> result = relationalMap.get(entityPath);

		if(result == null) {
			result = new RelationalPathBase<>(entityPath.getType(), entityPath.getMetadata(), table.schema(), table.name());
			relationalMap.put(entityPath, result);
		}

		return (RelationalPath<T>) result;
	}

}
