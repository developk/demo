package com.example.domain.models.datatyps;

import java.time.LocalDate;
import java.util.List;

public interface CustomDataTypesRepository {

	List<DataTypes> findWithDateRange(LocalDate from, LocalDate to);

	List<DataTypes> findWithDateRangeForH2(LocalDate from, LocalDate to);

}
