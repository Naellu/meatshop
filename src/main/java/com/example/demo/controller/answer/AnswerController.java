package com.example.demo.controller.answer;


import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.domain.answer.*;
import com.example.demo.service.answer.*;

@RestController()
@RequestMapping("answer")
public class AnswerController {
	
	@Autowired
	private AnswerService service;

	@PutMapping("update")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Answer answer) {
		Map<String, Object> res = service.update(answer);
		
		return ResponseEntity.ok().body(res);
	}

	@DeleteMapping("id/{id}")
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Integer id) {
		Map<String, Object> res = service.remove(id);
		
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("list")
	public List<Answer> list(@RequestParam("question") Integer questionId, Authentication authentication) {
		
		return service.list(questionId);
	}
	
	@PostMapping("add")
	public ResponseEntity<Map<String, Object>> add (
			@RequestBody Answer answer,
			Authentication authentication) {
		Map<String, Object> res = service.add(answer);
		System.out.println(res);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("id/{id}")
	public Answer get(@PathVariable("id") Integer id) {
		return service.get(id);
	}
	
	@PostMapping("respond")
	public ResponseEntity<Map<String, Object>> respondToQuestion(
			@RequestParam("questionId") Integer questionId,
			@RequestParam("content") String content,
			Authentication authentication) {
		
		Answer answer = new Answer();
		answer.setQuestionId(questionId);
		answer.setContent(content);
		answer.setAdminId(authentication.getName());
		
		Map<String, Object> res = service.add(answer);
		
		return ResponseEntity.ok().body(res);
	}
}
