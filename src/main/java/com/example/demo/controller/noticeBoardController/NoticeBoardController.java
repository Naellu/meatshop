package com.example.demo.controller.noticeBoardController;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.NoticeBoardDomain.*;
import com.example.demo.service.noticeBoardSerivice.*;

@Controller
@RequestMapping("noticeBoard")
public class NoticeBoardController {

	@Autowired
	private NoticeBoardService service;
	
	@GetMapping("list")
	public String list(Model model) {
		List<NoticeBoard> list = service.getList();
//		Map<String, Object> result = service.getList();
		model.addAttribute("noticeBoardList", list);
		return "noticeBoard/list";
	}
	
	@GetMapping("/id/{id}")
	public String noticeBoard(@PathVariable("id") Integer id, Model model) {
		NoticeBoard nboard = service.getNoticeBoard(id);
		model.addAttribute("nboard", nboard);
		System.out.println(nboard);
		return "noticeBoard/get";
	}
}
