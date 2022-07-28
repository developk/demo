package com.example.app1;

import com.example.app1.utils.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {CommonUtils.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CommonUtilsTests {

	@Autowired
	private CommonUtils commonUtils;

	@DisplayName("isNull 테스트 1")
	@Test
	void testIsNull() {
		Assert.isTrue(commonUtils.isNull(null), "참");
	}

	@DisplayName("isNull 테스트 2")
	@Test
	void testIsNullFalse() {
		Assertions.assertFalse(commonUtils.isNull("abc"), "참");
	}

}
