package com.example.domain.models.userinfo.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoProjection {

	@ToString.Include(name = "유저정보 순번", rank = 4)
	private Long userInfoId;
	@ToString.Include(name = "이름", rank = 3)
	private String name;
	@ToString.Include(name = "나이", rank = 2)
	private int age;
	@ToString.Include(name = "비고", rank = 1)
	private String remark;

	@QueryProjection
	public UserInfoProjection(
			Long userInfoId,
			String name,
			int age,
			String remark
	) {
		this.userInfoId = userInfoId;
		this.name = name;
		this.age = age;
		this.remark = remark;
	}

}
