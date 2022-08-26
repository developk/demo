package com.example.app1.controller;

import com.example.app1.dto.res.AlarmInfo;
import com.example.app1.dto.res.WebsocketSessionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class NotifyController {

	private final WebsocketEndpoints endpoint;

	public NotifyController(WebsocketEndpoints endpoint) {
		this.endpoint = endpoint;
	}

	@PostMapping(value = "/notice/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> broadcast(
			@Valid @NotNull @PathVariable(value = "userId") String userId,
			@Valid @RequestBody AlarmInfo alarmInfo) {
		endpoint.broadcast(userId, alarmInfo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/notice/targetList")
	public ResponseEntity<List<WebsocketSessionInfo>> getSessionList() {
		return new ResponseEntity<>(this.endpoint.getClientsList(), HttpStatus.OK);
	}

	@PostMapping(value = "/notice", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> broadcastAll(
			@Valid @RequestBody AlarmInfo alarmInfo) {
		endpoint.broadcast(alarmInfo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
