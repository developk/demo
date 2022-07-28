package com.example.app1.utils;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonUtils {

	public boolean isNull(Object value) {
		return ObjectUtils.isEmpty(value);
	}

	public boolean isNull(String value) {
		return StringUtils.isBlank(value);
	}

	public static String getStringValueFromMap(Map<String , ?> map, String key) {
		return MapUtils.getString(map, key);
	}
}
