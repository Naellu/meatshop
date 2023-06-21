package com.example.demo.controller.admin;

import java.io.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.admin.*;
import com.example.demo.service.mail.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@Controller("adminProductController")
@RequestMapping("/admin/product/")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('admin')")
public class ProductController {

	private final ProductService productService;

	private final MailService mailService;

	// 상품 목록 페이지
	@GetMapping("list")
	public String list(Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "stockQuantity", required = false) Integer stockQuantity,
			@RequestParam(value = "pub", required = false) String pub) {
		Map<String, Object> result = productService.getViewList(page, type, search, stockQuantity, pub);
		model.addAllAttributes(result);
		return "admin/product/list";
	}

	// 상품 공개 비공개 처리
	@PostMapping("pub")
	public ResponseEntity<Map<String, Object>> pubProc(@RequestBody PubRequest pub) {
		boolean ok = productService.pubProductAll(pub);

		if (ok) {
			Map<String, Object> response = Map.of("message", "공개처리 되었습니다.");
			return ResponseEntity.ok().body(response);
		} else {
			Map<String, Object> response = Map.of("message", "설정에 오류가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	// 상품 상세 페이지
	@GetMapping("detail/{id}")
	public String detail(Model model,
			@PathVariable("id") Integer id) {
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
	public String regProc(Product product,
			@RequestParam("files") MultipartFile[] files,
			RedirectAttributes rttr) {

		try {
			boolean ok = productService.add(product, files);
			if (ok) {
				rttr.addFlashAttribute("message", "상품이 등록되었습니다.");
				return "redirect:/admin/product/list";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rttr.addFlashAttribute("message", "상품 등록에 실패하였습니다.");
		return "redirect:/admin/product/list";

	}

	// 상품 수정 페이지
	@GetMapping("modify/{id}")
	public String modify(@PathVariable("id") Integer id,
			Model model) {
		ProductView product = productService.getOneView(id);
		model.addAttribute("product", product);
		return "admin/product/modify";
	}

	// 상품 수정 처리
	@PostMapping("modify/{id}")
	public String modifyProc(Product product,
			@RequestParam(value = "removeFiles", required = false) List<String> removeFileNames,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			RedirectAttributes rttr) {
		Integer productId = product.getProductId();
		rttr.addAttribute("productId", productId);
		try {
			boolean ok = productService.modify(product, removeFileNames, files);
			if (ok) {
				rttr.addFlashAttribute("message", productId + "번 상품이 수정되었습니다.");
				return "redirect:/admin/product/detail/{productId}";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rttr.addFlashAttribute("message", "게시물이 수정되지 않았습니다.");
		return "redirect:/admin/product/modify/{productId}";
	}

	// 상품 삭제 처리
	@PostMapping("remove")
	public String removeProc(@RequestParam Integer id, RedirectAttributes rttr) {
		Boolean ok = productService.remove(id);
		rttr.addAttribute("id", id);
		if (ok) {
			rttr.addFlashAttribute("message", "상품이 삭제되었습니다.");
			return "redirect:/admin/product/list";
		} else {
			rttr.addFlashAttribute("message", "상품 삭제를 실패하였습니다.");
			return "redirect:/admin/product/detail/{id}";
		}
	}

	@PostMapping("notify")
	@ResponseBody
	public void mailNotify(@RequestParam Integer productId) {
		mailService.sendNotifyEmail(productId);
	}

}
