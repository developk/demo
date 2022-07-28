package com.example.domain.models.account;

import com.example.domain.models.userinfo.QUserInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.domain.models.account.QAccount.account;

@Slf4j
public class CustomAccountRepositoryImpl extends QuerydslRepositorySupport implements CustomAccountRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public CustomAccountRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
		super(Account.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public List<Account> findAllAccount() {
		log.info("AccountRepositoryCustomImpl :: findAll");
		return from(account)
				       .fetch();
	}

	public Page<Account> findAllAccountPaging(Pageable pageable) {
		List<Account> content = jpaQueryFactory.selectFrom(account)
				                          .join(account.userInfo, QUserInfo.userInfo)
				                          .offset(pageable.getOffset())
				                          .limit(pageable.getPageSize())
				                        .fetchJoin()
				                      .fetch();


		return new PageImpl<>(
				content,
				pageable,
				content.size()
		);
	}

	@Override
	public Slice<Account> findAllAccountSlice(Pageable pageable) {

		log.info("AccountRepositoryCustomImpl :: findAllAccountSlice");

		List<Account> results = jpaQueryFactory.selectFrom(account)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize() + 1)
				.fetch();

		boolean hasNext = false;
		if (results.size() > pageable.getPageSize()) {
			results.remove(pageable.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(results, pageable, hasNext);
	}

	@Override
	public <T> List<T> findAllAccountCustomType(Class<T> resultType) {

		log.info("generic findAllAccountCustomType");

		QUserInfo userInfo = new QUserInfo("userInfo");

		JPQLQuery<T> query = jpaQueryFactory
				                  .select(
										  Projections.constructor(resultType,
												  account.id.as("accountId"),
												  account.name.as("accountName"),
												  userInfo.id.as("userId"),
												  userInfo.name.as("name"),
												  userInfo.age.as("age").intValue(),
												  userInfo.remark.as("remark"))
				                  )
				                  .from(account)
				                  .leftJoin(account.userInfo, userInfo)
				                     .orderBy(account.id.desc())
				;

		return query.fetch();
	}

}
