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
@RequestMapping("product/inquiry")
public class ProductInquiryController {

	@Autowired
	private ProductInquiryService service;

	@GetMapping("list")
	public String getListByProductId(
			ProductInquiry productInquiry,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			Model model) {
		Map<String, Object> result = service.showInquiryListByProductId(productInquiry, page);
		model.addAllAttributes(result);
		
		return "product/inquiry/list";
	}

	@GetMapping("add")
	public void addFrom(ProductInquiry productInquiry) {
	}

	@PostMapping("add")
	public ResponseEntity<Map<String, Object>> addProcess(@RequestBody ProductInquiry productInquiry) {

		Map<String, Object> res = new HashMap<>();

		boolean ok = service.addInquiry(productInquiry);
		if (ok) {
			res.put("message", "문의가 등록되었습니다.");
			return ResponseEntity.ok().body(res);
		} else {
			return ResponseEntity.status(403).build();
		}
	}

	@PostMapping("delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody ProductInquiry productInquiry) {

		Map<String, Object> res = new HashMap<>();
		

		boolean ok = service.deleteInquiry(productInquiry.getInquiryId());

		if (ok) {
			res.put("message", "문의가 삭제되었습니다.");
			return ResponseEntity.ok().body(res);
		} else {
			return ResponseEntity.status(403).build();
		}

	}

	@GetMapping("modify")
	public String modifyFrom(Integer inquiryId, Model model) {
	    ProductInquiry inquiry = service.getInquiry(inquiryId);

	    model.addAttribute("productInquiry", inquiry);
	    

	    return "product/inquiry/modify";
	}

	@PostMapping("modify")
	public ResponseEntity<Map<String, Object>> modifyProcess(@RequestBody ProductInquiry productInquiry) {

		
		Map<String, Object> res = new HashMap<>();

		boolean ok = service.modifyInquiry(productInquiry);
		if (ok) {
			res.put("message", "문의가 수정되었습니다.");
			return ResponseEntity.ok().body(res);
		} else {
			return ResponseEntity.status(403).build();
		}
	}

}
