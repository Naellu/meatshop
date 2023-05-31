package com.example.demo.domain.cart;

import com.example.demo.domain.Product;
import lombok.Data;

@Data
public class CartItem {
	private Integer id;
	private Integer cartId;
	private Integer productId;
	private Integer quantity;
	private Integer productPrice;
	
	private String productName;

	public static CartItem createCartItem(Integer productId, Integer quantity, Integer productPrice) {
		CartItem cartItem = new CartItem();
		cartItem.setProductId(productId);
		cartItem.setQuantity(quantity);
		cartItem.setProductPrice(productPrice);
		return cartItem;
	}

	public void addCount(int quantity) {
		this.quantity += quantity;
	}
	
	public void updateQuantity(int quantity) {
		this.quantity = quantity;
	}

}
