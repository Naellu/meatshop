package com.example.demo.controller.wishlist;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.wishlist.*;

import lombok.*;

@Controller("wishListController")
@RequestMapping("/wish/")
@RequiredArgsConstructor
public class WishListController {

	private final WishListService wishService;

	@GetMapping("list/{id}")
	public String wishList(Model model,
			@PathVariable("id") String memberId,
			Authentication authentication) {
		model.addAttribute("memberId", memberId);
		return "wish/list";
	}

	@PostMapping("delete")
	public String wishDeleteProc(@RequestBody WishList wishList) {
		boolean ok = wishService.remove(wishList);
		return null;
	}

	/*
	 * @GetMapping("view") public ResponseEntity<List<WishListView>>
	 * viewWishlist(@RequestParam("memberId") String memberId) { // memberId를 사용하여
	 * 해당 멤버의 찜 목록을 조회하는 로직 작성 List<WishListView> productList =
	 * wishService.getViewList(memberId);
	 * 
	 * // productList를 JSON 형태로 응답 return ResponseEntity.ok(productList); }
	 */

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("view")
	@ResponseBody
	public List<WishListView> viewWishlist(@RequestParam("memberId") String memberId) {
		// memberId를 사용하여 해당 멤버의 찜 목록을 조회하는 로직 작성
		List<WishListView> productList = wishService.getViewList(memberId);

		// productList를 JSON 형태로 응답
		return productList;
	}

	@PostMapping("like")
	public ResponseEntity<Map<String, Object>> like(@RequestBody WishList wishList, Authentication authentication) {

		if (authentication == null) {
			return ResponseEntity
					.status(403)
					.body(Map.of("message", "로그인 후 가능합니다."));
		} else {
			Map<String, Object> res = wishService.like(authentication, wishList);
			return ResponseEntity.ok(res);
		}
	}
	
	/*
	 * @PostMapping("like")
	 * 
	 * @ResponseBody public Map<String, Object> like(@RequestBody WishList wishList,
	 * Authentication authentication, HttpServletResponse response) { if
	 * (authentication == null) {
	 * response.setStatus(HttpServletResponse.SC_FORBIDDEN); return
	 * Map.of("message", "로그인 후 가능합니다."); } else { Map<String, Object> res =
	 * wishService.like(authentication, wishList);
	 * response.setStatus(HttpServletResponse.SC_OK); return res; } }
	 */
}
