package com.example.demo.controller.chat;

import java.util.*;

import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.chat.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Controller
@Slf4j
@RequestMapping("/chat/")
@RequiredArgsConstructor
public class ChatController {

	private final ChatService chatService;

	@GetMapping("list")
	public String chat() {
		return "chat/room";
	}

	@GetMapping("customer")
	public String customerChat(Model model, Authentication authentication) {
		if (authentication == null) {
			model.addAttribute("name", "guest");
		} else {
			model.addAttribute("name", authentication.getName());
		}

		return "chat/customer";
	}

	// 방페이지
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("admin/list")
	public String room(Model model, Authentication authentication) {
		model.addAttribute("name", authentication.getName());
		return "admin/chat/list";
	}

	// 방 생성하기
	@PostMapping("createRoom")
	@ResponseBody
	public List<ChatRoom> createRoom(@RequestParam HashMap<Object, Object> params) {
		String roomName = (String) params.get("roomName");
		return chatService.createRoom(roomName);
	}

	// 방 정보가져오기
	@PostMapping("getRoom")
	@ResponseBody
	public List<ChatRoom> getRoom(@RequestParam HashMap<Object, Object> params) {
		return chatService.getRoom();
	}

	@GetMapping("moveChating")
	public String chating(Model model, @RequestParam HashMap<Object, Object> params) {
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		log.info("요청옴!!!");
		List<ChatRoom> newList = chatService.getChatRoomsByRoomNumber(roomNumber);
		if (newList != null && newList.size() > 0) {
			model.addAttribute("roomName", params.get("roomName"));
			model.addAttribute("roomNumber", params.get("roomNumber"));
			return "chat/room";
		} else {
			return "/";
		}
	}

	@GetMapping("admin/moveChating")
	@PreAuthorize("hasAuthority('admin')")
	public String adminchating(Model model, @RequestParam HashMap<Object, Object> params) {
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		List<ChatRoom> newList = chatService.getChatRoomsByRoomNumber(roomNumber);
		if (newList != null && newList.size() > 0) {
			model.addAttribute("roomName", params.get("roomName"));
			model.addAttribute("roomNumber", params.get("roomNumber"));
			return "admin/chat/room";
		} else {
			return "chat/list";
		}
	}
}