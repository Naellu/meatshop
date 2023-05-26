package com.example.demo.domain;

import lombok.*;

@Data
public class CartItem {
	private Integer quantity; // 주문 수량(상품 옵션)
	private Integer productId;
	private Double price;
}
