package com.example.domain.models.datatyps;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "DATA_TYPES")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "INT_VAL")
	private Integer intVal;

	@Column(name = "LONG_VAL")
	private Long longVal;

	@Column(name = "DECIMAL_VAL")
	private BigDecimal decimalVal;

	@Column(name = "STRING_VAL")
	private String stringVal;

	@Column(name = "DATE_VAL")
	private LocalDate dateVal;

	@Column(name = "DATE_TIME_VAL")
	private LocalDateTime dateTimeVal;

}
