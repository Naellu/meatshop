package com.example.demo.controller.faqController;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.faqDomain.*;
import com.example.demo.service.faqService.*;

@Controller
@RequestMapping("faq")
public class FaqController {

	@Autowired
	private FaqService service;
	
	@GetMapping("list")
	public String list(Model model) {
		
		List<Faq> list = service.getList();
		
		model.addAttribute("list", list);
		
		return "faq/list";
	}
}
