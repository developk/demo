package com.example.app1;

import com.example.app1.utils.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class DemoApplicationTests {

	private static final String siteCode = "BP241";

	private static final String sitePassword = "jpOMs0aojOuM";

	@Test
	void contextLoads() {
	}

	private String makeTestPlainText() {

		String requestNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS")) + "33001";
		String authType = "M";
		String popType = "Y";
		String customize = "";
		String gender = "";
		String successUrl = "http://127.0.0.1:5500/success.html";
		String errorUrl = "http://127.0.0.1:5500/fail.html";

		return "7:REQ_SEQ" + requestNo.getBytes().length + ":" + requestNo +
				       "8:SITECODE" + siteCode.getBytes().length + ":" + siteCode +
				       "9:AUTH_TYPE" + authType.getBytes().length + ":" + authType +
				       "7:RTN_URL" + successUrl.getBytes().length + ":" + successUrl +
				       "7:ERR_URL" + errorUrl.getBytes().length + ":" + errorUrl +
				       "11:POPUP_GUBUN" + popType.getBytes().length + ":" + popType +
				       "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize +
				       "6:GENDER" + gender.getBytes().length + ":" + gender;
	}

	private String getEncryptErrorMessage(int code) {
		return switch (code) {
			case 0 -> "정상";
			case -1 -> "암호화 시스템 에러입니다.";
			case -2 -> "암호화 처리오류입니다.";
			case -3 -> "암호화 데이터 오류입니다.";
			case -9 -> "입력 데이터 오류입니다.";
			default -> "알수 없는 에러 입니다. code : " + code;
		};
	}

	private String getDecryptErrorMessage(int code) {
		return switch (code) {
			case 0 -> "정상";
			case -1 -> "복호화 시스템 오류입니다.";
			case -4 -> "복호화 처리 오류입니다.";
			case -5 -> "복호화 해쉬 오류입니다.";
			case -6 -> "복호화 데이터 오류입니다.";
			case -9 -> "입력 데이터 오류입니다.";
			case -12 -> "사이트 패스워드 오류입니다.";
			default -> "알수 없는 에러 입니다. code : " + code;
		};
	}

	/**
	 * @deprecated 샘플 JSP 상에 들어 있는 함수지만, 복호화 되지 않음.
	 * 2022-06-30 nice측에 문의 결과, URLDecode.decode 통해 복호화
	 * 하도록 가이드 받음.
	 * @param paramValue
	 * @param gubun
	 * @return
	 */
	private String requestReplace (String paramValue, String gubun) {

		String result = "";

		if (paramValue != null) {

			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");

			if(gubun != "encodeData"){
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}

			result = paramValue;

		}
		return result;
	}
	

	@DisplayName("나이스 암호화 체크")
	@Test
	void encryptNiceModule() {

		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

		String sMessage = "";
		String sEncData = "";

		String plainText = makeTestPlainText();

		int iReturn = niceCheck.fnEncode(siteCode, sitePassword, plainText);
		if( iReturn == 0 ) {
			sEncData = niceCheck.getCipherData();
		}
		sMessage = getEncryptErrorMessage(iReturn);


		System.out.println("sEncData: " + sEncData + ", sMessage: " + sMessage);
		Assertions.assertEquals(iReturn, 0);
	}

	@DisplayName("나이스 복호화 체크")
	@Test
	void decryptNiceModule() throws Exception{

		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

		String rEncodeData = "AgAFQlAyNDGkuSVkjqmBTGP5ZVYpwhyDou57uAiiNylLUIgT7KOiSMc3KpEfbXkmMSYVWMU3oReU2q9BTbSUb7jY2pLjXCl9vkYdnJGuGIV/972EI14NDQn3iqNUPKtj3duD%2Bwvtb%2BMStUXoVufPv640yg1TH9bp7caHL2KuHqMbwr4KNWIqeeZ5kgy/6ir/n//H9oosxVYG6HUlrp0%2B%2BGi7U4ptewlGqvpnfDBAx69SYn0iKJwHSkjgcJdIGzGVBhVyMMFDq/rkmiiduIm9YaGB%2BLz8KpUZwUBNuVwaDYuhsOgDPbEkImfXtBL5StiB23LZS374wNxcXATAHW%2BF511dqKDQBku5NmrLon4MJJ5Fo/hr39e8185mLBO5ScRz9ie/X7mk1GZRqJU%2BNUu84FB5/qwguVna7mT6oWiH5LfUwNv6vSxvrJ34rF5O%2BFEFAlAb9FjBiHA%2BqLg7N0C/0fq/nar2Qyjwi%2B/Bu1pCHBfB7CXoi9i07lHR/QnpYgoCJFoBNFP8cz5lJsaV7XvGC7zquQPTEOgAMoIe/CIoJZwou8O6fm2Qgr9FF/CTnxbAfKsHppSRwMM=";

//		String sEncodeData = requestReplace(rEncodeData, "encodeData");

		String check = URLDecoder.decode(rEncodeData, StandardCharsets.UTF_8);

		String sDecodeData = "";
		String sCipherTime = "";
		String errorMessage = "";

		int eReturn = niceCheck.fnDecode(siteCode, sitePassword, check);
		if (eReturn == 0) {
			sDecodeData = niceCheck.getPlainData();
			sCipherTime = niceCheck.getCipherDateTime();
		}

		errorMessage = getDecryptErrorMessage(eReturn);

		System.out.println("Error: " + errorMessage + ", sDecodeData: " + sDecodeData + ", sCipherTime: " + sCipherTime);

		var resultMap = niceCheck.fnParse(sDecodeData);

		String requestNumber = CommonUtils.getStringValueFromMap(resultMap, "REQ_SEQ");
		String responseNumber = CommonUtils.getStringValueFromMap(resultMap, "RES_SEQ") ;
		String authType = CommonUtils.getStringValueFromMap(resultMap, "AUTH_TYPE"); // 문서상 M: 휴대폰 C: 카드 X: 인증서 P: 삼성패스
		String name = CommonUtils.getStringValueFromMap(resultMap, "NAME"); // 문서상 EUC_KR
		String utf8Name = URLDecoder.decode(CommonUtils.getStringValueFromMap(resultMap, "NAME"), StandardCharsets.UTF_8); // 문서상 UTF-8
		String birthDay = CommonUtils.getStringValueFromMap(resultMap, "BIRTHDATE");
		String gender = CommonUtils.getStringValueFromMap(resultMap, "GENDER");
		String nationalInfo = CommonUtils.getStringValueFromMap(resultMap, "NATIONALINFO");
		String mobileNo = CommonUtils.getStringValueFromMap(resultMap, "MOBILE_NO");
		String mobileCo = CommonUtils.getStringValueFromMap(resultMap, "MOBILE_CO");
		String di = CommonUtils.getStringValueFromMap(resultMap, "DI");
		String ci = CommonUtils.getStringValueFromMap(resultMap, "CI");
		String errors = CommonUtils.getStringValueFromMap(resultMap, "ERR_CODE");

		System.out.println("requestNumber: " + requestNumber);
		System.out.println("responseNumber: " + responseNumber);
		System.out.println("name: " + name);
		System.out.println("utf8Name: " + utf8Name);
		System.out.println("birthDay: " + birthDay);
		System.out.println("authType: " + authType);
		System.out.println("gender: " + gender);
		System.out.println("nationalInfo: " + nationalInfo);
		System.out.println("mobileNo: " + mobileNo);
		System.out.println("mobileCo: " + mobileCo);
		System.out.println("di: " + di);
		System.out.println("ci: " + ci);
		System.out.println("errors: " + errors);

		Assertions.assertEquals(eReturn, 0);
	}
	
}
