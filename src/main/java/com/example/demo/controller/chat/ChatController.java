package com.example.demo.controller.chat;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;

import lombok.extern.slf4j.*;

@Controller
@Slf4j
@RequestMapping("/chat/")
public class ChatController {
	List<ChatRoom> roomList = new ArrayList<>();
	static int roomNumber = 0;

	@GetMapping("customer")
	public String chat() {
		return "chat/customerchat";
	}

	// 방페이지
	@GetMapping("room")
	public String room() {
		return "chat/room";
	}

	// 방 생성하기
	@PostMapping("/createRoom")
	@ResponseBody
	public List<ChatRoom> createRoom(@RequestParam HashMap<Object, Object> params) {
		String roomName = (String) params.get("roomName");
		if (roomName != null && !roomName.trim().equals("")) {
			ChatRoom room = new ChatRoom();
			room.setRoomNumber(++roomNumber);
			room.setRoomName(roomName);
			roomList.add(room);
		}
		return roomList;
	}

	// 방 정보가져오기
	@PostMapping("/getRoom")
	@ResponseBody
	public List<ChatRoom> getRoom(@RequestParam HashMap<Object, Object> params) {
		log.info("log {}", roomList);
		return roomList;
	}

	// 채팅방
	@GetMapping("/moveChating")
	public String chating(Model model, @RequestParam HashMap<Object, Object> params) {
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		List<ChatRoom> newList = new ArrayList<>();
		for (ChatRoom room : roomList) {
			if (room.getRoomNumber() == roomNumber) {
				newList.add(room);
			}
		}
		if (newList != null && newList.size() > 0) {
			model.addAttribute("roomName", params.get("roomName"));

			model.addAttribute("roomNumber", params.get("roomNumber"));
			return "chat/customerchat";
		} else {
			return "chat/room";
		}
	}

}