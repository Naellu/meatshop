package com.example.demo.domain.payment;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentVerifyDto {
	
	private BigDecimal amount;
	private String merchantUid;
}
