package com.example.demo.controller.product;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.product.inquiry.*;

@Controller
@RequestMapping("product/answer")
public class ProductAnswerController {
	
	@Autowired
	private ProductInquiryAnswerService answerService;
	
	@Autowired
	private ProductInquiryService inquiryService;

	
	@PostMapping("add")
	public ResponseEntity<Map<String, Object>> addAnswer(@RequestBody ProductAnswer productAnswer) {
		Map<String, Object> res = new HashMap<>();
		
		boolean ok = answerService.addProductAnswer(productAnswer);
		if(ok) {
			res.put("message", "답변이 완료되었습니다.");
			return ResponseEntity.ok().body(res); 
		} else {
			return ResponseEntity.status(403).build(); 
		}
	}
	
	@GetMapping("get")
	@ResponseBody
	public ProductAnswer getAnswer(Integer inquiryId) {
		
		return answerService.getAnswer(inquiryId); 
	}
	
	@GetMapping("modify/{inquiryId}")
	public String modifyForm(@PathVariable("inquiryId") Integer inquiryId, Model model) {
		ProductInquiry productInquiry = inquiryService.getInquiry(inquiryId);
		ProductAnswer productAnswer = answerService.getAnswer(inquiryId);
		model.addAttribute("productInquiry", productInquiry);
		model.addAttribute("productAnswer", productAnswer);
		
		return "product/answer/modify";
		
	}
	
	
	@PostMapping("modify/{inquiryId}")
	public String modifyProcess(
			@PathVariable("inquiryId") Integer inquiryId,
			String answer,
//			@RequestBody 
			ProductAnswer productAnswer,
			Integer productId) {
		
		boolean ok = answerService.modifyAnswer(productAnswer);
		
		if(ok) {
			return "redirect:/product/inquiry/list?productId=" + productId;
		} else {
			return "redirect:/product/inquiry/list?productId=" + productId;
		}
	}
	
	@DeleteMapping("inquiryid/{inquiryid}")
	public ResponseEntity<Map<String, Object>>remove(@PathVariable("inquiryid") Integer inquiryid){
		
		Map<String, Object> res = answerService.remove(inquiryid);
		return ResponseEntity.ok(res);
	}
}


