package com.example.domain.models;

import com.example.domain.config.JPAConfig;
import com.example.domain.models.account.AccountRepository;
import com.example.domain.models.account.QAccount;
import com.example.domain.models.account.projection.AccountProjection;
import com.example.domain.models.account.projection.AccountProjectionWithPost;
import com.example.domain.models.account.projection.QAccountProjection;
import com.example.domain.models.account.projection.QAccountProjectionWithPost;
import com.example.domain.models.post.QPost;
import com.example.domain.models.post.projection.QPostProjection;
import com.example.domain.models.userinfo.QUserInfo;
import com.example.domain.models.userinfo.projection.QUserInfoProjection;
import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@Slf4j
@EnableAutoConfiguration
@ContextConfiguration(
		classes = {AccountRepository.class, JPAConfig.class}
)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountTests {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@DisplayName("기본 findAll")
	@Test
	void findAll() {
		repository.findAll();
	}

	@DisplayName("커스텀 findAll")
	@Test
	void findAccountById() {
		repository.findAllAccount();
	}

	@DisplayName("JPA 커스텀 Slice")
	@Test
	void sliceAccounts() {
		repository.findAllAccountSlice(PageRequest.of(0,10));
	}

	@DisplayName("JPA 커스텀 page")
	@Test
	void pageAccounts() {
		repository.findAllAccountPaging(PageRequest.of(1,10));
	}
	
	@DisplayName("QueryProjection 테스트1")
	@Test
	void queryProjectionTest1() {


		QAccount account = new QAccount("t1");
		QUserInfo quserInfo = new QUserInfo("t2");

		List<AccountProjection> resultSets = jpaQueryFactory
				.select(
						new QAccountProjection(
								account.id,
								account.name,
								new QUserInfoProjection(quserInfo.id, quserInfo.name, quserInfo.age, quserInfo.remark)
						)
				)
				.from(account)
				.leftJoin(account.userInfo, quserInfo)
				.where(
						account.id.gt(10)
				)
				.fetch()
				;

		resultSets.forEach(accountProjection -> log.info("data: " + accountProjection.toString()));

	}

	@DisplayName("QueryProjection 테스트2")
	@Test
	void queryProjectionTest2() {

		QAccount account = new QAccount("t1");
		QUserInfo userInfo = new QUserInfo("t2");
		QPost posts = new QPost("t3");

		List<AccountProjectionWithPost> resultSets =
				jpaQueryFactory
                     .from(account)
                     .leftJoin(account.userInfo, userInfo)
                     .leftJoin(account.posts, posts)
                     .where(
                             account.id.gt(10)
                     )
                     .transform(
							 GroupBy.groupBy(account.id).list(
									 new QAccountProjectionWithPost(
										account.id.as("accountId"),
										account.name.as("accountName"),
										account.userInfo.name.as("userName"),
										GroupBy.list(
												new QPostProjection(
														posts.id.as("postId"),
														account.name.as("accountName"),
														userInfo.name.as("userName"),
														posts.subject.as("subject"),
														posts.content.as("content"),
														posts.postedAt.as("postedAt")
												)
										)
									 )
							 )
                     );

		resultSets.forEach(accountProjection -> log.info("data: " + accountProjection.toString()));

	}


	@DisplayName("상수 SELECT 테스트")
	@Test
	void testConstant() {
		QAccount acc = new QAccount("acc");

		List<Tuple> tuple =
				jpaQueryFactory
						.select(
								Expressions.asNumber(1).as("num"),
								Expressions.asString("abc").as("str"),
								acc.id.as("id"),
								acc.name.as("name")
						)
						.from(acc)
						.fetch();

		tuple.forEach(
				tuple1 -> {
					log.info("num: {}, str: {}, id: {}, name: {}",
							tuple1.get(0, Long.class),
							tuple1.get(1, String.class),
							tuple1.get(2, Long.class),
							tuple1.get(3, String.class)
					);
				}
		);
	}

}
