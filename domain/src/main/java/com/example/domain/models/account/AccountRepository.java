package com.example.domain.models.account;

import com.example.domain.jpa.commons.CommonJpaRepository;

public interface AccountRepository extends CommonJpaRepository<Account, Long>, CustomAccountRepository {

}
