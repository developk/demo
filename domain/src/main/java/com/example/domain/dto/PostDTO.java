package com.example.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {

	private Long id;
	private Long accountId;
	private String subject;
	private String content;
	private LocalDateTime postedAt;

	@Builder
	public PostDTO(
			Long id,
			Long accountId,
			String subject,
			String content,
			LocalDateTime postedAt
	) {
		this.id = id;
		this.accountId = accountId;
		this.subject = subject;
		this.content = content;
		this.postedAt = postedAt;
	}

}
