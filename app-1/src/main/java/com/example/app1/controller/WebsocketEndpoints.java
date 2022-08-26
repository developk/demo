package com.example.app1.controller;


import com.example.app1.dto.res.AlarmInfo;
import com.example.app1.dto.res.WebsocketSessionInfo;
import com.example.app1.ws.codecs.AlarmMessageCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@ServerEndpoint(value = "/ws/notice/{userId}", encoders = AlarmMessageCodec.class)
public class WebsocketEndpoints {

	private static Map<String, Session> clients = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session session, @PathParam(value = "userId") String userId) throws IOException {

		log.info("신규 세션 접속: {}", session.getId());

		if (!clients.containsKey(userId)) {
			clients.put(userId, session);

			String message = MessageFormat.format("신규 세션 생성: {0}", userId);

			session.getBasicRemote().sendText(message);
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam(value = "userId") String userId) {
		log.info("신규 세션 접속 종료: {}", session.getId());
		clients.remove(userId);
	}

	public List<WebsocketSessionInfo> getClientsList() {
		List<WebsocketSessionInfo> sessions = new CopyOnWriteArrayList<>();
		for (String key : clients.keySet()) {
			Session session = clients.get(key);
			sessions.add(new WebsocketSessionInfo(session.getId(), key, session.getRequestURI().toString(), session.isOpen()));
		}
		return sessions;
	}

	public void broadcast(AlarmInfo alarmInfo) {
		for (String key : clients.keySet()) {
			notify(alarmInfo, clients.get(key));
		}
	}

	public void broadcast(String userId, AlarmInfo alarmInfo) {
		Session target = clients.get(userId);
		if (Objects.nonNull(target)) {
			notify(alarmInfo, target);
		}
	}

	public void notify(AlarmInfo alarmInfo, Session session) {
		if (session.isOpen()) {
			try {
				alarmInfo.setTargetSessionId(session.getId());
				session.getBasicRemote().sendObject(alarmInfo);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (EncodeException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
