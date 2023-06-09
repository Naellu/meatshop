package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@ToString
public class ChatRoom {
	int roomNumber;
	String roomName;

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
	}
}
