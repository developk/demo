package com.example.domain.models.post.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostProjection {

	@ToString.Include(name = "포스팅 순번", rank = 9)
	private Long postId;
	@ToString.Include(name = "포스팅 등록 계정이름", rank = 8)
	private String accountName;
	@ToString.Include(name = "포스팅 등록 계정 사용자 이름", rank = 7)
	private String userName;
	@ToString.Include(name = "포스팅 제목", rank = 6)
	private String subject;
	@ToString.Include(name = "포스팅 본문", rank = 5)
	private String content;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime postedAt;

	@QueryProjection
	public PostProjection(
			Long postId,
			String accountName,
			String userName,
			String subject,
			String content,
			LocalDateTime postedAt
	) {
		this.postId = postId;
		this.accountName = accountName;
		this.userName = userName;
		this.subject = subject;
		this.content = content;
		this.postedAt = postedAt;
	}

	@ToString.Include(name = "포스팅 등록일시", rank = 4)
	private String postedAtString() {
		if (Objects.isNull(this.postedAt)) {
			return null;
		}

		return this.postedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	}

}
