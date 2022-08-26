package com.example.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
	private Long id;
	private String name;

	@Builder
	public AccountDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
