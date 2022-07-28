package com.example.app1.controller;

import com.example.app1.dto.res.UserAccountReadInfo;
import com.example.app1.utils.CommonUtils;
import com.example.domain.models.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class IndexController {

	private final AccountService accountService;
	private final CommonUtils commonUtils;

	@GetMapping("/")
	public String hello() {
		this.accountService.findAll();
		this.accountService.findOne(1L);
		this.accountService.getAccounts(UserAccountReadInfo.class);
		return "hello world!";
	}

	@GetMapping("/getEncData")
	public String getEncData() {

		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

		String requestNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS")) + "33001";
		String siteCode = "BP241";
		String sitePassword = "jpOMs0aojOuM";
		String authType = "M";
		String popType = "Y";
		String customize = "";
		String gender = "";
		String successUrl = "http://localhost:8080/checkplus_success";
		String errorUrl = "http://localhost:8080/checkplus_fail";

		String plainText = new StringBuilder()
				                   .append("7:REQ_SEQ" + requestNo.getBytes().length + ":" + requestNo)
				                   .append("8:SITECODE" + siteCode.getBytes().length + ":" + siteCode)
				                   .append("9:AUTH_TYPE" + authType.getBytes().length + ":" + authType)
				                   .append("7:RTN_URL" + successUrl.getBytes().length + ":" + successUrl)
				                   .append("7:ERR_URL" + errorUrl.getBytes().length + ":" + errorUrl)
				                   .append("11:POPUP_GUBUN" + popType.getBytes().length + ":" + popType)
				                   .append("9:CUSTOMIZE" + customize.getBytes().length + ":" + customize)
				                   .append("6:GENDER" + gender.getBytes().length + ":" + gender)
				                   .toString();

		String sMessage = "";
		String sEncData = "";


		int iReturn = niceCheck.fnEncode(siteCode, sitePassword, plainText);
		if( iReturn == 0 )
		{
			sEncData = niceCheck.getCipherData();
		}
		else if( iReturn == -1)
		{
			sMessage = "암호화 시스템 에러입니다.";
		}
		else if( iReturn == -2)
		{
			sMessage = "암호화 처리오류입니다.";
		}
		else if( iReturn == -3)
		{
			sMessage = "암호화 데이터 오류입니다.";
		}
		else if( iReturn == -9)
		{
			sMessage = "입력 데이터 오류입니다.";
		}
		else
		{
			sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
		}

		return sEncData;
	}

}
