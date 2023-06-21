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
	// HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	private List<HashMap<String, Object>> roomList = new ArrayList<>(); // 웹소켓 세션을 담아둘 리스트 ---roomListSessions

	private Gson gson = new Gson();

	// 웹소켓 연결이 되면 동작
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결시 session의 id를 키로 세션저장
		super.afterConnectionEstablished(session);

		// url에서 roomNumber를 추출해서 roomList에담을것임.
		boolean flag = false;
		String url = session.getUri().toString();
		String roomNumber = url.split("/chat/")[1];
		int idx = roomList.size(); // 방의 사이즈를 조사한다.

		if (roomList.size() > 0) {
			for (int i = 0; i < roomList.size(); i++) {
				String roomNum = (String) roomList.get(i).get("roomNumber");
				if (roomNum.equals(roomNumber)) {
					flag = true;
					idx = i;
					break;
				}
			}
		}

		if (flag) { // 존재하는 방이라면 세션만 추가한다.
			HashMap<String, Object> map = roomList.get(idx);
			map.put(session.getId(), session);
		} else { // 최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			roomList.add(map);
		}

		// 세션 등록이 끝나면 발급받은 세션 ID값의 메시지를 발송한다.
		Map<String, String> messageData = new HashMap<>();
		messageData.put("type", "getId");
		messageData.put("sessionId", session.getId());
		String json = gson.toJson(messageData);
		session.sendMessage(new TextMessage(json));
	}

	// 메시지를 수신하면 실행
	// 상속받은 TextWebSocketHandler는 handleTextMessage를 실행시키고
	// 메시지 타입에따라 handleBinaryMessage또는 handleTextMessage가 실행된다.
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// 메시지 발송
		String msg = message.getPayload();
		JsonObject obj = jsonToObjectParser(msg);
		String roomNum = obj.get("roomNumber").getAsString();

		HashMap<String, Object> temp = new HashMap<>();
		if (roomList.size() > 0) {
			for (int i = 0; i < roomList.size(); i++) {
				String roomNumber = (String) roomList.get(i).get("roomNumber"); // 세션리스트의 저장된 방번호를 가져와서
				if (roomNumber.equals(roomNum)) { // 같은값의 방이 존재한다면
					temp = roomList.get(i); // 해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					break;
				}
			}

			// 해당 방의 세션들만 찾아서 메시지를 발송해준다.
			for (String k : temp.keySet()) {
				if (k.equals("roomNumber")) { // 다만 방번호일 경우에는 건너뛴다.
					continue;
				}

				WebSocketSession wss = (WebSocketSession) temp.get(k);
				if (wss != null) {
					try {
						String json = gson.toJson(obj);
						wss.sendMessage(new TextMessage(json));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		session.close();
		// 소켓 종료
		if (roomList.size() > 0) { // 소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for (int i = 0; i < roomList.size(); i++) {
				roomList.get(i).remove(session.getId());
			}
		}
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

/*
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
*/