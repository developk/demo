package com.example.domain.models.post;

import com.example.domain.models.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;
	@Column(length = 150, nullable = false)
	private String subject;
	@Lob
	private String content;
	@Column(updatable = false)
	private LocalDateTime postedAt;

	@Builder
	public Post(
			final Long id,
			String subject,
			final Account account,
			String content,
			final LocalDateTime postedAt
	) {
		this.id = id;
		this.subject = subject;
		this.account = account;
		this.content = content;
		this.postedAt = postedAt;
	}

}
