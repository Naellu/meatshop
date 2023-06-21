package com.example.demo.controller.product;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.product.review.*;

@Controller
@RequestMapping("/product/review")
public class ReviewController {

	@Autowired
	private ReviewService service;

	@GetMapping("list")
	public String getListByProductId(
			Review review,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			Authentication authentication,
			Model model) {

		Map<String, Object> result;
		if (authentication != null) {
			result = service.showReviewListByProductId(review, page, authentication);
		} else {
			// 로그인하지 않은 사용자를 처리하는 방법을 정의합니다.
			result = service.showReviewListByProductId(review, page, null);
		}
		model.addAllAttributes(result);

		return "product/review/list";
	}

	@GetMapping("add")
	public void addFrom(Review review, Model model) {
		String productName = service.getproductName(review);
		
		model.addAttribute("productName", productName);
		model.addAttribute("review", review);
		
		
	}

	@PostMapping("add")
	public String add(
			Review review,
			@RequestParam("files") MultipartFile[] files,
			RedirectAttributes rttr) throws Exception {

		boolean ok = service.addReview(review, files);

		if (ok) {
			rttr.addFlashAttribute("message", "리뷰가 등록되었습니다.");
			return "redirect:/product/info/" + review.getProductId();
		} else {
			rttr.addFlashAttribute("message", "(오류) 리뷰가 등록되지 않았습니다.");
			return "redirect:/product/info/" + review.getProductId();
		}

	}

	@PostMapping("remove")
	public String remove(Review review, RedirectAttributes rttr) {
		boolean ok = service.remove(review.getReviewId());
		if (ok) {
			rttr.addFlashAttribute("message", "리뷰가 삭제되었습니다..");
			return "redirect:/product/info/" + review.getProductId();
		} else {
			rttr.addFlashAttribute("message", "(오류) 리뷰가 삭제되지 않았습니다.");
			return "redirect:/product/info/" + review.getProductId();
		}
	}

	@GetMapping("modify")
	public String modifyForm(Integer reviewId, Model model) {
		Map<String, Object> result = service.getReview(reviewId);
		model.addAllAttributes(result);
		return "product/review/modify";
	}

	@PostMapping("modify")
	public String modifyProcess(
			Review review,
			@RequestParam(value = "files", required = false) MultipartFile[] addFiles,
			@RequestParam(value = "removeFiles", required = false) List<String> removeFileNames,
			RedirectAttributes rttr) throws Exception {

		boolean ok = service.modify(review, addFiles, removeFileNames);

		if (ok) {
			// 해당 게시물 보기로 리디렉션
			rttr.addFlashAttribute("message", "리뷰가 수정되었습니다.");
			return "redirect:/product/info/" + review.getProductId();
		} else {
			// 수정 form 으로 리디렉션
			rttr.addFlashAttribute("message", "(오류)리뷰가 수정되지 않았습니다.");
			return "redirect:/product/info/" + review.getProductId();
		}
	}

	@PostMapping("like")
	public ResponseEntity<Map<String, Object>> like(
			@RequestBody ReviewLike reviewLike,
			Authentication authentication) {
		if (authentication != null) {
			return ResponseEntity.ok().body(service.reviewLike(reviewLike, authentication));

		} else {
			return ResponseEntity.status(403)
					.body(Map.of("message", "좋아요를 누르기 전에 로그인 해주세요"));
		}

	}

}
