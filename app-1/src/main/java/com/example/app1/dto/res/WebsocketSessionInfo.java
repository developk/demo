package com.example.app1.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsocketSessionInfo {

	private String sessionId;
	private String userId;
	private String requestUri;
	private boolean open;

	public WebsocketSessionInfo(String sessionId, String userId, String requestUri, boolean open) {
		this.sessionId = sessionId;
		this.userId = userId;
		this.requestUri = requestUri;
		this.open = open;
	}
}
