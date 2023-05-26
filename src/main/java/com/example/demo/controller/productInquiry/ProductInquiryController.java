package com.example.demo.controller.productInquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.inquiry.*;


@Controller
@RequestMapping("productinquiry")
public class ProductInquiryController {
	
	@Autowired
	private ProductInquiryService service;

	@GetMapping("test")
	public void test() {
		
	}
	
	@GetMapping("list")
	public String getListByProductId(
			Integer productId, 
			@RequestParam(value="page",defaultValue = "1") Integer inquirys,
			Model model
			) {
		
		Map<String, Object> result = service.showInquiryListByProductId(productId,inquirys);
		
		model.addAllAttributes(result);
		
		return "productinquiry/list";
	}
	
	@GetMapping("add")
	public void addFrom(Integer productId) {
		
	}
	
	@PostMapping("add")
	public String addProcess(ProductInquiry productInquiry,
			RedirectAttributes rttr) {
		
		boolean ok = service.addInquiry(productInquiry);
		if (ok) {
			rttr.addFlashAttribute("message", "문의가 등록되었습니다.");
			return "redirect:/productinquiry/list?productId="+ productInquiry.getProductId(); 
		} else {
			rttr.addFlashAttribute("message", "문의가 등록되지 않았습니다.");
			return "redirect:/productinquiry/add?productId=" + productInquiry.getProductId(); 
		}
	}
	
	@PostMapping("delete")
	public String delete(ProductInquiry productInquiry, RedirectAttributes rttr) {
		
		System.out.println(productInquiry.getInquiryId());
		
		boolean ok = service.deleteInquiry(productInquiry.getInquiryId());
		
		
		if(ok) {
			rttr.addFlashAttribute("message", "문의가 삭제되었습니다.");
			return "redirect:/productinquiry/list?productId=" + productInquiry.getProductId();
		} else {
			rttr.addFlashAttribute("message", "문의가 삭제되지 않았습니다.");
			return "redirect:/productinquiry/list?productId=" + productInquiry.getProductId();
		}
		
	}
	
	@GetMapping("modify/{inquiryId}")
	public String modifyFrom(@PathVariable("inquiryId") Integer inquiryId, Model model) {
		model.addAttribute("productInquiry", service.getInquiry(inquiryId));
		return "productinquiry/modify";
	}
	
	@PostMapping("modify/{inquiryId}")
	public String modifyProcess(@PathVariable("inquiryId") Integer inquiryId,
			ProductInquiry productInquiry,
			RedirectAttributes rttr
			) {
		boolean ok = service.modifyInquiry(productInquiry);
		
		if(ok) {
			rttr.addFlashAttribute("message", "문의가 수정되었습니다.");
			return "redirect:/productinquiry/list?productId=" + productInquiry.getProductId();
		} else {
			rttr.addFlashAttribute("message", "문의가 수정되지 않았습니다.");
			return "redirect:/productinquiry/list?productId=" + productInquiry.getProductId();
		}

	}
	

}
