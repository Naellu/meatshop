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
	
	@GetMapping("view")
	public ResponseEntity<List<WishListView>> viewWishlist(@RequestParam("memberId") String memberId) {
	    // memberId를 사용하여 해당 멤버의 찜 목록을 조회하는 로직 작성
	    List<WishListView> productList = wishService.getViewList(memberId);
	    
	    // productList를 JSON 형태로 응답
	    return ResponseEntity.ok(productList);
	}
}
