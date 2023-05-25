package com.example.demo.domain.cart;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
	private Integer id;
	private String memberId;

	public static Cart createCart(String memberId) {
		Cart cart = new Cart();
		cart.setMemberId(memberId);
		return cart;
	}
}
