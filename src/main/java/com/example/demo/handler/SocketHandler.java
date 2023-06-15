package com.example.demo.handler;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.*;

import com.google.gson.*;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class SocketHandler extends TextWebSocketHandler {
	// 웹소켓 세션을 담아둘 맵
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();

	// 웹소켓 연결이 되면 동작
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결시 session의 id를 키로 세션저장
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		JsonObject obj = new JsonObject();
		obj.addProperty("type", "getId");
		obj.addProperty("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toString()));
	}

	// 메시지를 수신하면 실행
	// 상속받은 TextWebSocketHandler는 handleTextMessage를 실행시키고
	// 메시지 타입에따라 handleBinaryMessage또는 handleTextMessage가 실행된다.
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// 메시지 발송
		String msg = message.getPayload();
		log.info("log: {}", msg);
		JsonObject obj = jsonToObjectParser(msg);
		for (String key : sessionMap.keySet()) {
			// sessionMap에서 WebSocketSession객체 얻기
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(obj.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 웹소켓 종료시 동작
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 소켓 종료시 세션id를 기준으로 세션삭제
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}

	private static JsonObject jsonToObjectParser(String jsonStr) {
		JsonObject obj = null;
		try {
			obj = JsonParser
					.parseString(jsonStr)
					.getAsJsonObject();
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
