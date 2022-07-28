package com.example.domain.models.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CustomAccountRepository {

	List<Account> findAllAccount();

	<T> List<T> findAllAccountCustomType(Class<T> resultType);

	Slice<Account> findAllAccountSlice(Pageable pageable);

	Page<Account> findAllAccountPaging(Pageable pageable);

}
