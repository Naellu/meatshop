package com.example.demo.service.chat;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.demo.domain.*;

@Service
public class ChatServiceImpl implements ChatService {
	private List<ChatRoom> roomList = new ArrayList<>();
	private static int roomNumber = 0;

	@Override
	public List<ChatRoom> createRoom(String roomName) {
		if (roomName != null && !roomName.trim().equals("")) {
			ChatRoom room = new ChatRoom();
			room.setRoomNumber(++roomNumber);
			room.setRoomName(roomName);
			roomList.add(room);
		}
		return roomList;
	}

	@Override
	public List<ChatRoom> getRoom() {
		return roomList;
	}

	@Override
	public List<ChatRoom> getChatRoomsByRoomNumber(int roomNumber) {
		List<ChatRoom> newList = new ArrayList<>();
		for (ChatRoom room : roomList) {
			if (room.getRoomNumber() == roomNumber) {
				newList.add(room);
			}
		}
		return newList;
	}
}
