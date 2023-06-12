package com.example.demo.handler;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.*;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class ChatHandler extends TextWebSocketHandler {
	
	private static List<WebSocketSession> list = new ArrayList<>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload : " + payload);

		for (WebSocketSession sess : list) {
			sess.sendMessage(message);
		}
	}

	/* Client가 접속 시 호출되는 메서드 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		list.add(session);

		log.info(session + " 클라이언트 접속");
	}

	/* Client가 접속 해제 시 호출되는 메서드 */

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		log.info(session + " 클라이언트 접속 해제");
		list.remove(session);
	}
}
