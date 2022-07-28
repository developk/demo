package com.example.domain.models.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	public List<Account> findAll() {
//		return this.accountRepository.findAll();
		return this.accountRepository.findAllAccount();
	}

	public List<Account> findOne(Long id) {
		return this.accountRepository.findAllAccount();
	}

	public <T> List <T> getAccounts(Class<T> type) {
		return this.accountRepository.findAllAccountCustomType(type);
	}

	public Slice<Account> sliceAccounts() {
		return this.accountRepository.findAll(PageRequest.of(1, 10));
	}

}
