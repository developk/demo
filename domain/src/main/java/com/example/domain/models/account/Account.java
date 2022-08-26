package com.example.domain.models.account;

import com.example.domain.models.post.Post;
import com.example.domain.models.userinfo.UserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	UserInfo userInfo;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Post> posts = new ArrayList<>();

	@Builder
	public Account(Long id, String name, final UserInfo userInfo) {
		this.id = id;
		this.name = name;
		this.userInfo = userInfo;
	}

}
