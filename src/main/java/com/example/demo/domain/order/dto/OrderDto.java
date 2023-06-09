package com.example.demo.domain.order.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.*;
import com.example.demo.domain.order.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Integer id;
	private String memberId;
	private Long totalPrice;
	private LocalDate created;
	private Status status;
	

	private List<Integer> productIds;
	private List<String> productName; // 주문에 들어있는 주문상품들

}
