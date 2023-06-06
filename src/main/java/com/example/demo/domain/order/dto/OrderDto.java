package com.example.demo.domain.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.domain.order.OrderItem;
import com.example.demo.domain.order.Status;

import lombok.Data;

@Data
public class OrderDto {
	private Integer id;
	private String memberId;
	private Long totalPrice;
	private LocalDateTime created;
	private String status;
	
	private List<String> productName; // 주문에 들어있는 주문상품들
}
