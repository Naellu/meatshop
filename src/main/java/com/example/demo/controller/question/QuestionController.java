package com.example.demo.controller.question;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.question.*;
import com.example.demo.service.question.*;

@Controller
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService service;

	@GetMapping("list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "search", defaultValue = "") String search) {
		Map<String, Object> result = service.getList(page, search);
		model.addAllAttributes(result);
		
		System.out.println(result);
		
		return "question/list";
	}

	@GetMapping("/id/{id}")
	public String question(@PathVariable("id") Integer id, Model model) {
		Question question = service.getQuestion(id);
		model.addAttribute("question", question);

		return "question/get";
	}

	@GetMapping("add")
	public void addForm() {

	}

	@PostMapping("add")
	public String addProcess(@RequestParam("files") MultipartFile[] files, Question question, RedirectAttributes rttr)
			throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserId = authentication.getName(); // 현재 사용자의 아이디

		// 현재 사용자의 아이디를 Question 객체의 customerId 필드에 설정합니다.
		question.setCustomerId(currentUserId);

		boolean ok = service.addQuestion(question, files);

		if (ok) {
			rttr.addFlashAttribute("message", "게시물이 등록되었습니다.");
			return "redirect:/question/list";
		} else {
			rttr.addFlashAttribute("message", "게시물이 등록되지 않았습니다.");
			return "redirect:/question/add";
		}
	}
}
