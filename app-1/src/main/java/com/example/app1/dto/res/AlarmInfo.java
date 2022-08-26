package com.example.app1.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlarmInfo {

	private String link;
	private String title;
	private String content;

	@Hidden
	@Setter
	private String targetSessionId;

	@Hidden
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime sendTime = LocalDateTime.now();

	@Builder
	public AlarmInfo(String link, String title, String content) {
		this.link = link;
		this.title = title;
		this.content = content;
	}


}
