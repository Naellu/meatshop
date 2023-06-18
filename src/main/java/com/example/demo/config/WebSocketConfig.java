package com.example.demo.config;

import org.springframework.context.annotation.*;
import org.springframework.web.socket.config.annotation.*;

import com.example.demo.handler.*;

import lombok.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final SocketHandler socketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
		.addHandler(socketHandler, "/chat/{roomNumber}");
	}
}
