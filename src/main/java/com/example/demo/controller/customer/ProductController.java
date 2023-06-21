package com.example.demo.controller.customer;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.customer.*;

import lombok.*;

@Controller("customerProductController")
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	// 상품 목록 페이지
	@GetMapping("list")
	public String list() {
		// Map<String, Object> result = productService.getViewList(page, categoryId,
		// type, search);
		// model.addAllAttributes(result);
		return "product/list";
	}

	// 상품 목록 view
	@GetMapping("listView")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> listView(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "category", required = false) Integer categoryId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "search", defaultValue = "") String search,
			Authentication authentication) {

		Map<String, Object> result = productService.getViewList(page, categoryId, type, search, authentication);
		return result;
	}

	// 상품 정보 페이지
	@GetMapping("info/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		ProductView product = productService.getOneView(id);
		model.addAttribute("product", product);
		return "product/info";
	}
}

/*
@GetMapping("listView")
public ResponseEntity<Map<String, Object>> listView(
		@RequestParam(value = "page", defaultValue = "1") Integer page,
		@RequestParam(value = "category", required = false) Integer categoryId,
		@RequestParam(value = "type", required = false) String type,
		@RequestParam(value = "search", defaultValue = "") String search,
		Authentication authentication) {

	Map<String, Object> result = productService.getViewList(page, categoryId, type, search, authentication);
	return ResponseEntity.ok(result);
}
*/

