package com.example.demo.controller.productInquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.service.*;
import com.example.demo.service.inquiry.*;


@Controller
@RequestMapping("inquiry")
public class ProductInquiryController {
	
	@Autowired
	private ProductInquiryService service;

	@GetMapping("test")
	public void test() {
		
	}
	
	@GetMapping("list")
	public String list(Integer id, Model model) {
		
		Map<String, Object> result = service.showInquiryList(id);
		
		model.addAllAttributes(result);
		
		return "inquiry/list";
	}
	
	@GetMapping("add")
	public void addFrom(Integer id) {
		
	}
	
	@PostMapping("add")
	public String add(Integer productId ,String userNickname, String inquiryTitle, String inquiryText,
			RedirectAttributes rttr) {
		boolean ok = service.addInquiry(productId, userNickname, inquiryTitle, inquiryText);
		if (ok) {
			rttr.addFlashAttribute("message", "문의가 등록되었습니다.");
			return "redirect:/inquiry/list?id="+ productId; 
		} else {
			rttr.addFlashAttribute("message", "문의가 등록되지 않았습니다.");
			return "redirect:/inquiry/add?id=" + productId;
		}
	}
	
	@PostMapping("delete")
	public String delete(Integer inquiryId, Integer productId, RedirectAttributes rttr) {
		boolean ok = service.deleteInquiry(inquiryId);
		System.out.println("-----controller----");
		System.out.println(inquiryId);
		System.out.println("-----------------");
		
		if(ok) {
			rttr.addFlashAttribute("message", "문의가 삭제되었습니다.");
			return "redirect:/inquiry/list?id=" + productId;
		} else {
			rttr.addFlashAttribute("message", "문의가 삭제되지 않았습니다.");
			return "redirect:/inquiry/list?id=" + productId;
		}
		
	}
	
}
