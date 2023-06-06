package com.example.demo.domain.order.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDtoTest {
	private Integer id;
	private String memberId;
	private Long totalPrice;
	private LocalDateTime created;
	private String status;
	
	private String productName; // 주문에 들어있는 주문상품들
}
