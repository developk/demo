package com.example.domain.models.userinfo;

import com.example.domain.jpa.commons.CommonJpaRepository;

public interface UserInfoRepository extends CommonJpaRepository<UserInfo, Long>, CustomUserInfoRepository {
}
