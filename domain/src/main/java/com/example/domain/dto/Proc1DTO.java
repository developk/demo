package com.example.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Proc1DTO {

	private List<AccountDTO> accounts = new ArrayList<>();
	private List<PostDTO> posts = new ArrayList<>();

	@Builder
	public Proc1DTO(
			List<AccountDTO> accounts,
			List<PostDTO> posts
	) {
		this.accounts = accounts;
		this.posts = posts;
	}

}
