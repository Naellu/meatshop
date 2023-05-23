package com.example.demo.controller.admin;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.admin.*;

import lombok.*;

@Controller("adminProductController")
@RequestMapping("/admin/product/")
@RequiredArgsConstructor
public class ProductController {

	private final AdminProductService productService;

	@GetMapping("list")
	public String list(Model model) {
		List<ProductView> productList = productService.getViewList();
		model.addAttribute("productList", productList);
		return "admin/product/list";
	}
	
	@GetMapping("detail")
	public String detail(Model model, Integer id) {
		ProductView product = productService.getOneView(id);
		model.addAttribute("product", product);
		return "admin/product/detail";
	}
	
	@PostMapping("remove")
	public String remove(Integer id, RedirectAttributes rttr) {
		Boolean ok = productService.remove(id);
		if (ok) {
			rttr.addFlashAttribute("message", "상품이 삭제되었습니다.");
			return "redirect:/admin/product/list";
		} else {
			rttr.addFlashAttribute("message", "상품 삭제를 실패하였습니다.");
			return "redirect:/admin/product/detail?id=" + id;
		}
	}
}
