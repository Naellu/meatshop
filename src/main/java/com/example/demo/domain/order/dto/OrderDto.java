package com.example.demo.domain.order.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.order.Status;

import lombok.Data;

@Data
public class OrderDto {
	private Integer id;
	private String memberId;
	private Long totalPrice;
	private LocalDate created;
	private Status status;
	
	private List<String> productName; // 주문에 들어있는 주문상품들
}
