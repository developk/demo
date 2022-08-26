package com.example.domain.models.userinfo;

import com.example.domain.models.account.Account;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(length = 20)
	private String name;

	@Column
	private int age;

	@Column
	@Builder.Default
	private String remark = "";

}
