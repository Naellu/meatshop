package com.example.demo.controller.cart;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.cart.dto.CartItemDto;
import com.example.demo.service.cart.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	// 상품상세에서 장바구니 담기 
	@PostMapping
	public ResponseEntity<String> addCart(@RequestBody CartItemDto cartItemDto,
										  Authentication authentication) {
		// 회원 인증정보 확인
		if(authentication == null || !authentication.isAuthenticated() ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		String memberId = authentication.getName();
		// 회원id, 상품id, 구매개수, 가격 받아서 저장
		cartService.makeOrderByCartItems(memberId, cartItemDto.getProductId(), cartItemDto.getQuantity(), cartItemDto.getPrice());
		
		return ResponseEntity.status(HttpStatus.OK).body("상품을 담았습니다");
	}

	// 장바구니 불러오기
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public String getCart(Model model, Authentication authentication) {

		// 회원id 가져오기
		String memberId = authentication.getName();

		// 회원id로 장바구니 찾기
		Cart memberCart = cartService.findCartByMemberId(memberId);

		// 장바구니에서 cartitem를 리스트로 가져오기
		List<CartItem> cartItems = cartService.findAllItems(memberCart);

		// List<CartItem>을 뷰에 전달
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("memberId", memberId);

		return "cart/list";
	}
	
	
	// 장바구니 항목 삭제
	@PostMapping("/delete")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String deleteCartItem(@RequestBody CartItemDto cartItemDto, Authentication authentication) {
		
		Integer cartItemId = cartItemDto.getId();
		
		boolean isdeleted = cartService.deleteCartItem(cartItemDto);
		
		if (isdeleted) {
			return "장바구니에서 상품을 삭제하였습니다";
		} else {
			return "장바구니에서 상품을 삭제하지 못했습니다";
		}
	}
	
	// 장바구니 항목 수량 업데이트
	@PostMapping("/update")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String updateItemQuantity(@RequestBody CartItemDto cartItemDto) {
		cartService.updateCartItemQuantity(cartItemDto);
		
		return "수량 갱신 완료";
	}
	
	
	
}
