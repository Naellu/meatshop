package com.example.demo.controller.productInquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.inquiry.*;

@Controller
@RequestMapping("productanswer")
public class ProductAnswerController {
	
	@Autowired
	private ProductAnswerService service;

	
	@PostMapping("add")
	public ResponseEntity<Map<String, Object>> addAnswer(@RequestBody ProductAnswer productAnswer) {
		Map<String, Object> res = new HashMap<>();
		
		boolean ok = service.addProductAnswer(productAnswer);
		if(ok) {
			res.put("message", "답변이 완료되었습니다.");
			return ResponseEntity.ok().body(res); 
		} else {
			return ResponseEntity.status(403).build(); 
		}
	}
	
	@GetMapping("get")
	@ResponseBody
	public List<Object> getAnswer(Integer inquiryId) {
		
		return service.getAnswer(inquiryId); 
	}
	
}
