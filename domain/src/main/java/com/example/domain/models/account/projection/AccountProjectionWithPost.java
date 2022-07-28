package com.example.domain.models.account.projection;

import com.example.domain.models.post.projection.PostProjection;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountProjectionWithPost {

	@ToString.Include(name = "계정 등록 순번", rank = 9)
	private Long accountId;

	@ToString.Include(name = "계정명", rank = 8)
	private String accountName;

	@ToString.Include(name = "사용자이름", rank = 7)
	private String userName;

	@ToString.Include(name = "포스팅", rank = 6)
	List<PostProjection> posts = new ArrayList<>();

	@QueryProjection
	public AccountProjectionWithPost(
			Long accountId,
			String accountName,
			String userName,
			List<PostProjection> posts
	) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.userName = userName;
		this.posts = posts;
	}

}
