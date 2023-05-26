package com.example.demo.controller.noticeBoardController;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.NoticeBoardDomain.*;
import com.example.demo.service.noticeBoardSerivice.*;

@Controller
@RequestMapping("noticeBoard")
public class NoticeBoardController {

	@Autowired
	private NoticeBoardService service;
	
	@GetMapping("list")
	public String list(Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "type", required = false) String type) {
//		List<NoticeBoard> list = service.getList();
		Map<String, Object> result = service.getList(page, search, type);
		
		model.addAllAttributes(result);
		
		return "noticeBoard/list";
	}
	
	@GetMapping("/id/{id}")
	public String noticeBoard(@PathVariable("id") Integer id, Model model) {
		NoticeBoard nboard = service.getNoticeBoard(id);
		model.addAttribute("nboard", nboard);
		
		return "noticeBoard/get";
	}
	
	@GetMapping("/modify/{id}")
	public String modifyForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("nboard", service.getNoticeBoard(id));
		
		return "noticeBoard/modify";
	}
	
	@PostMapping("/modify/{id}")
	public String modifyProcess(NoticeBoard nboard, 
			@RequestParam(value = "files", required = false) MultipartFile[] addFiles,
			@RequestParam(value = "removeFiles", required = false) List<String> removeFileNames,
			RedirectAttributes rttr) throws Exception {
		
		boolean ok = service.modify(nboard, addFiles, removeFileNames);
		
		if (ok) {
			rttr.addFlashAttribute("message", "게시물이 수정되었습니다.");
			return "redirect:/noticeBoard/id/" + nboard.getId();
		} else {
			rttr.addFlashAttribute("message", "게시물이 수정되지 않았습니다.");
			return "redirect:/noticeBoard/modify/" + nboard.getId();
		}
	}
	
	@PostMapping("remove")
	public String remove(Integer id, RedirectAttributes rttr) {
		
		boolean ok = service.remove(id);
		
		if (ok) {
			rttr.addFlashAttribute("message", "게시물이 삭제되었습니다.");
			return "redirect:/noticeBoard/list";
		} else {
			rttr.addFlashAttribute("message", "게시물이 삭제되지 않았습니다.");
			return "redirect:/noticeBoard/id/" + id;
		}
	}
	
	@GetMapping("add")
	public void addForm() {
		
	}
	
	@PostMapping("add")
	public String addProcess(@RequestParam("files") MultipartFile[] files,
			NoticeBoard nboard,
			RedirectAttributes rttr) throws Exception {
		
		nboard.setWriter("매니저");
		
		boolean ok = service.addNoticeBoard(nboard, files);
		
		if (ok) {
			rttr.addFlashAttribute("message", "게시물이 등록되었습니다.");
			return "redirect:/noticeBoard/list";
		} else {
			rttr.addFlashAttribute("message", "게시물이 등록되지 않았습니다.");
			return "redirect:/noticeBoard/add";
		}
	}
}
