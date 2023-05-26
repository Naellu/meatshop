package com.example.demo.controller.cart;

import com.example.demo.domain.Members;
import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	// 장바구니에 물건 담기
	@GetMapping("/add")
	@ResponseBody
	public String addCartItem() {

		String memberId = "user05";
		CartItem cartItem = CartItem.createCartItem(26, 4, 25000);

		// 장바구니에 cartItem 담기
		cartService.makeOrderByCartItems(memberId,
										cartItem.getProductId(),
										cartItem.getQuantity(),
										cartItem.getProductPrice());

		return "cart add ok";
	}

	// 장바구니 불러오기
	@GetMapping
	public String getCart(Model model) {

		// 회원id 가져오기 - 테스트데이터
		String memberId = "user05";

		// 회원id로 장바구니 찾기
		Cart memberCart = cartService.findCartByMemberId(memberId);
		log.info("cart={}",memberCart);

		// 장바구니에서 cartitem를 리스트로 가져오기
		List<CartItem> cartItems = cartService.findAllItems(memberCart);
		log.info("cartItems={}", cartItems);

		// List<CartItem>을 뷰에 전달
		model.addAttribute("cartItems", cartItems);

		return "cart/list";
	}


	
}
