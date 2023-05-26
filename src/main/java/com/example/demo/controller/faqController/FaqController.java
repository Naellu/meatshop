package com.example.demo.controller.faqController;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.faqDomain.*;
import com.example.demo.service.faqService.*;

@Controller
@RequestMapping("faq")
public class FaqController {

	@Autowired
	private FaqService service;
	
	@GetMapping("list")
	public String list(Model model) {
		
		List<Faq> faq = service.getList();
		
		model.addAttribute("faq", faq);
		
		return "faq/list";
	}
	
//	@PostMapping("remove")
//	public String remove(Integer id, RedirectAttributes rttr) {
//		
//		boolean ok = service.remove(id);
//		
//		if (ok) {
//			rttr.addFlashAttribute("message", "게시물이 삭제되었습니다.");
//			return "redirect:/faq/list";
//		} else {
//			rttr.addFlashAttribute("message", "게시물이 삭제되지 않았습니다.");
//			return "redirect:/faq/list";
//		}
//	}
}
