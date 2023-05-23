package com.example.demo.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Order {
	private Integer id;
	
	// 회원
	// 주문상품(OrderItem)
	// 주문상태
	
	private LocalDateTime created;
	private Long totalPrice; 
}
