package com.example.app1.ws.codecs;

import com.example.app1.dto.res.AlarmInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.*;

public class AlarmMessageCodec implements Encoder.Text<AlarmInfo>, Decoder.Text<AlarmInfo> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@Override
	public AlarmInfo decode(String s) throws DecodeException {
		try {
			return objectMapper.readValue(s, AlarmInfo.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean willDecode(String s) {
		return StringUtils.isNotBlank(s);
	}

	@Override
	public String encode(AlarmInfo object) throws EncodeException {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}
