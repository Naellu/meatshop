package com.example.demo.controller.customer;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.customer.*;

import lombok.*;

@Controller("customerProductController")
@RequestMapping("/customer/product/")
@RequiredArgsConstructor
public class ProductController {
	private final ProductServiceImpl productService;

	// 상품 목록 페이지
	@GetMapping("list")
	public String list(Model model) {
		List<ProductView> productList = productService.getViewList();
		model.addAttribute("productList", productList);
		return "customer/product/list";
	}

	// 상품 상세 페이지
	@GetMapping("detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		ProductView product = productService.getOneView(id);
		model.addAttribute("product", product);
		return "customer/product/detail";
	}
	

	
	

}
