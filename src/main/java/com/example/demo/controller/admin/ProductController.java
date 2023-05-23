package com.example.demo.controller.admin;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.admin.*;

import lombok.*;

@Controller("adminProductController")
@RequestMapping("/admin/product/")
@RequiredArgsConstructor
public class ProductController {

	private final AdminProductService productService;

	// 상품 목록 페이지
	@GetMapping("list")
	public String list(Model model) {
		List<ProductView> productList = productService.getViewList();
		model.addAttribute("productList", productList);
		return "admin/product/list";
	}

	// 상품 상세 페이지
	@GetMapping("detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		ProductView product = productService.getOneView(id);
		model.addAttribute("product", product);
		return "admin/product/detail";
	}

	// 상품 등록 페이지
	@GetMapping("reg")
	public String reg() {
		return "admin/product/reg";
	}

	// 상품 등록 처리
	@PostMapping("reg")
	public String regProc(Product product, @RequestParam("files") MultipartFile[] files, RedirectAttributes rttr) {
		try {
			boolean ok = productService.add(product, files);
			if (ok) {
				rttr.addFlashAttribute("message", "상품이 등록되었습니다.");
				return "redirect:/admin/product/list";
			} else {
				rttr.addFlashAttribute("message", "상품 등록에 실패하였습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/product/list";
	}

	// 상품 수정 페이지
	@GetMapping("modify/{id}")
	public String modify(@PathVariable("id") Integer id, Model model) {
		ProductView product = productService.getOneView(id);
		model.addAttribute("product", product);
		return "admin/product/modify";
	}

	// 상품 수정 페이지
	@PostMapping("modify/{id}")
	public String modifyProc(Product product,
			@RequestParam(value = "removeFiles", required = false) List<String> removeFileNames,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			RedirectAttributes rttr) {
		try {
			boolean ok = productService.modify(product, removeFileNames, files);
			if (ok) {
				rttr.addFlashAttribute("message", product.getProductId() + "번 상품이 수정되었습니다.");
				return "redirect:/admin/product/detail/" + product.getProductId();
			} else {
				// 수정폼으로 리디렉션
				rttr.addFlashAttribute("message", "게시물이 수정되지 않았습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/product/modify/" + product.getProductId();
	}

	// 상품 삭제 처리
	@PostMapping("remove")
	public String removeProc(Integer id, RedirectAttributes rttr) {
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
