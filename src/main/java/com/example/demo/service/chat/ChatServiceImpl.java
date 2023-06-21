package com.example.demo.service.chat;

import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import com.example.demo.domain.*;

import lombok.extern.slf4j.*;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
	private List<ChatRoom> roomList = new ArrayList<>();
	private static int roomNumber = 0;

	@Override
	public List<ChatRoom> createRoom(String roomName) {
		boolean isExistingRoom = false;
		for (ChatRoom room : roomList) {
			if (room.getRoomName().equals(roomName)) {
				isExistingRoom = true;
				break;
			}
		}

		if (!isExistingRoom) {
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
	public List<ChatRoom> getChatRoomsByRoomNumber(Integer roomNumber) {
		List<ChatRoom> newList = new ArrayList<>();
		for (ChatRoom room : roomList) {
			if (room.getRoomNumber() == roomNumber) {
				newList.add(room);
			}
		}
		return newList;
	}

	@Override
	public void removeRoom(int roomNumber) {
		Iterator<ChatRoom> iterator = roomList.iterator();
		while (iterator.hasNext()) {
			ChatRoom room = iterator.next();
			if (room.getRoomNumber() == roomNumber) {
				iterator.remove();
			}
		}
	}
}
