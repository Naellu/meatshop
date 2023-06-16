package com.example.demo.domain.order.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.*;
import com.example.demo.domain.order.Status;

import lombok.Data;

@Data
public class OrderDto {
	private Integer id;
	private String memberId;
	private Long totalPrice;
	private LocalDate created;
	private Status status;
	
	private Integer productId;
	
	private List<Product> productInfoes; // 주문에 들어있는 주문상품들
}
