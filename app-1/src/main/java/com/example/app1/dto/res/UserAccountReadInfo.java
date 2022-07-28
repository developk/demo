package com.example.app1.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAccountReadInfo {

	private long accountId;
	private String accountName;
	private long userId;
	private String name;
	private int age;
	private String remark;

}
