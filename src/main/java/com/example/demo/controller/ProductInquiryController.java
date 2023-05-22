package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.*;


@Controller
@RequestMapping("inquiry")
public class ProductInquiryController {
	
	@Autowired
	private ProductInquiryService service;

	@GetMapping("/test")
	public void test() {
		
	}
	
	@GetMapping("/list")
	public String list(Integer id, Model model) {
		
		Map<String, Object> result = service.showList(id);
		
		model.addAllAttributes(result);
		
		return "inquiry/list";
	}
	
}
