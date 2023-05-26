package com.example.demo.controller.product;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.product.inquiry.*;


@Controller
@RequestMapping("product/inquiry")
public class ProductInquiryController {
	
	@Autowired
	private ProductInquiryService service;

	@GetMapping("test")
	public void test() {
		
	}
	
	@GetMapping("list")
	public String getListByProductId(
			ProductInquiry productInquiry, 
			@RequestParam(value="page",defaultValue = "1") Integer page,
			Model model
			) {
		Map<String, Object> result = service.showInquiryListByProductId(productInquiry,page);
		result.put("customerId", "hoimin");
		model.addAllAttributes(result);
		
		return "product/inquiry/list";
	}
	
	@GetMapping("add")
	public void addFrom(ProductInquiry productInquiry) {
	}
	
	@PostMapping("add")
	public String addProcess(ProductInquiry productInquiry,
			RedirectAttributes rttr) {
		
		boolean ok = service.addInquiry(productInquiry);
		if (ok) {
			rttr.addFlashAttribute("message", "문의가 등록되었습니다.");
			return "redirect:/product/info/"+ productInquiry.getProductId(); 
		} else {
			rttr.addFlashAttribute("message", "문의가 등록되지 않았습니다.");
			return "redirect:/product/info/"+ productInquiry.getProductId(); 
		}
	}
	
	@PostMapping("delete")
	public String delete(ProductInquiry productInquiry, RedirectAttributes rttr) {
		
		System.out.println(productInquiry.getInquiryId());
		
		boolean ok = service.deleteInquiry(productInquiry.getInquiryId());
		
		
		if(ok) {
			rttr.addFlashAttribute("message", "문의가 삭제되었습니다.");
			return "redirect:/product/info/"+ productInquiry.getProductId();
		} else {
			rttr.addFlashAttribute("message", "문의가 삭제되지 않았습니다.");
			return "redirect:/product/info/"+ productInquiry.getProductId();
		}
		
	}
	
	@GetMapping("modify/{inquiryId}")
	public String modifyFrom(@PathVariable("inquiryId") Integer inquiryId, Model model) {
		model.addAttribute("productInquiry", service.getInquiry(inquiryId));
		return "product/inquiry/modify";
	}
	
	@PostMapping("modify/{inquiryId}")
	public String modifyProcess(@PathVariable("inquiryId") Integer inquiryId,
			ProductInquiry productInquiry,
			RedirectAttributes rttr
			) {
		boolean ok = service.modifyInquiry(productInquiry);
		
		if(ok) {
			rttr.addFlashAttribute("message", "문의가 수정되었습니다.");
			return "redirect:/product/inquiry/list?productId=" + productInquiry.getProductId();
		} else {
			rttr.addFlashAttribute("message", "문의가 수정되지 않았습니다.");
			return "redirect:/product/inquiry/list?productId=" + productInquiry.getProductId();
		}

	}
	

}
