package com.example.demo.controller.product;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.product.review.*;

@Controller
@RequestMapping("/product/review")
public class ProductReviewController {
	
	
	@Autowired
	private ReviewService service;
	
	@GetMapping("list")
	public String getListByProductId(
			Review review,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			Model model) {
		Map<String, Object> result = service.showReviewListByProductId(review, page);
		model.addAllAttributes(result);

		return "product/review/list";
	}
	
	@GetMapping("add")
	public void addFrom(Review review) {
		
		
	}

	@PostMapping("add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody Review review){
		System.out.println(review);
		Map<String, Object> res = new HashMap<>();
		
		boolean ok = service.addReview(review);
		
		if(ok) {
			res.put("message", "문의가 등록되었습니다.");
			return ResponseEntity.ok().body(res);
		} else {
			return ResponseEntity.status(403).build();
		}
		
	}
	
}
