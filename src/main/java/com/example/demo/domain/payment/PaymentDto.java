package com.example.demo.domain.payment;

import java.util.List;

import lombok.Data;

@Data
public class PaymentDto {
	private Integer orderId;
	private List<String> productName;
	private Integer totalPrice;
	private String email;
	private String memberName;
	private String phoneNumber;
	private String address;
}
