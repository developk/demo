package com.example.domain.models.account.projection;


import com.example.domain.models.userinfo.projection.UserInfoProjection;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountProjection {
	@ToString.Include(name = "계정 등록 순번", rank = 9)
	private Long accountId;
	@ToString.Include(name = "이름", rank = 8)
	private String name;
	@ToString.Include(name = "유저정보", rank = 7)
	private UserInfoProjection userInfo;

	@QueryProjection
	public AccountProjection(Long accountId, String name, UserInfoProjection userInfo) {
		this.accountId = accountId;
		this.name = name;
		this.userInfo = userInfo;
	}

}
