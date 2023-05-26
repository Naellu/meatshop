package com.example.demo.domain;

import lombok.*;

@Data
public class OrderItem {
	
	private Integer id;
	private Integer quantity; // 주문 수량(상품 옵션)
	private Integer productId;
	private Double price;
	
	// 주문 가격
	// 상품(Item)
	// 주문(Order)
	
}
