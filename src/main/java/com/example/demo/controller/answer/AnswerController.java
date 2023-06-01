package com.example.demo.controller.answer;


import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.answer.*;
import com.example.demo.domain.question.*;
import com.example.demo.service.answer.*;

@RestController()
@RequestMapping("answer")
public class AnswerController {
	
	@Autowired
	private AnswerService service;

	@PutMapping("update")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Answer answer) {
		Map<String, Object> res = service.update(answer);
		
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("id/{id}")
	public Answer get(@PathVariable("id") Integer id) {
		return service.get(id);
	}

	@DeleteMapping("id/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Integer id) {
		Map<String, Object> res = service.remove(id);
		
		return ResponseEntity.ok().body(res);
	}
	
	@PostMapping("add")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Map<String, Object>> add (
			@RequestBody Answer answer,
			Authentication authentication) {
		Map<String, Object> res = service.add(answer);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("list")
	public List<Answer> list(@RequestParam("question") Integer questionId, Authentication authentication) {

		return service.list(questionId);
	}
	
}
