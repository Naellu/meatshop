package com.example.demo.service.chat;

import java.util.*;

import com.example.demo.domain.*;

public interface ChatService {

	List<ChatRoom> createRoom(String roomName);

	List<ChatRoom> getRoom();

	List<ChatRoom> getChatRoomsByRoomNumber(int roomNumber);

}
