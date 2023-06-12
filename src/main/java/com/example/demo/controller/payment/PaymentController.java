package com.example.demo.controller.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/payments")
public class PaymentController {
	
	@Value("{iamport.rest.api.key}")
	private String apiKey;
	
	@Value("{iamport.rest.api.secret}")
	private String apiSecret;
	
	@PostMapping("/prepare")
	public void prepare() {
		// TODO : 결제 사전검증
	}
	
	
}
