package com.example.demo.controller.product;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.product.review.*;

@Controller
@RequestMapping("/product/review")
public class ProductReviewController {
	
	
	@Autowired
	private ReviewService service;
	
	@GetMapping("list")
	public String getListByProductId(
			Review review,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			Model model) {
		Map<String, Object> result = service.showReviewListByProductId(review, page);
		model.addAllAttributes(result);

		return "product/review/list";
	}
	
	@GetMapping("add")
	public void addFrom(Review review) {
		
		
	}

	@PostMapping("add")
	public String add(
			Review review, 
			@RequestParam("files") MultipartFile[] files,
			RedirectAttributes rttr) throws Exception {
			
		
		boolean ok = service.addReview(review, files);
		
		if(ok) {
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
		if(ok) {
			rttr.addFlashAttribute("message", "리뷰가 삭제되었습니다..");
			return "redirect:/product/info/" + review.getProductId();
		} else {
			rttr.addFlashAttribute("message", "(오류) 리뷰가 삭제되지 않았습니다.");
			return "redirect:/product/info/" + review.getProductId();
		}
	}
	
	@GetMapping("modify")
	public String modifyForm(Integer reviewId, Model model) {
		model.addAttribute("review", service.getReview(reviewId));
		return "product/review/modify";
	}
	
	@PostMapping("modify")
	public String modifyProcess(
			Review review,
			@RequestParam(value="files", required = false) MultipartFile[] addFiles,
			@RequestParam(value="removeFiles", required = false) List<String> removeFileNames,
			RedirectAttributes rttr
			) throws Exception {
		
		System.out.println(review);
		System.out.println(removeFileNames);
		for(MultipartFile file : addFiles) {
			System.out.println(file.getOriginalFilename());
		}
		boolean ok = service.modify(review, addFiles, removeFileNames);

		if (ok) {
			// 해당 게시물 보기로 리디렉션
//			rttr.addAttribute("success", "modify");
			rttr.addFlashAttribute("message", "리뷰가 수정되었습니다.");
			return "redirect:/product/info/" + review.getProductId();
		} else {
			// 수정 form 으로 리디렉션
//			rttr.addAttribute("fail", "modify");
			rttr.addFlashAttribute("message", "(오류)리뷰가 수정되지 않았습니다.");
			return "redirect:/product/info/" + review.getProductId();
		}
	}
	
	
}
