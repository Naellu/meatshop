package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@GetMapping("/list")
	public void getOrderPage(Model model) {
		// 회원이 구매(주문)버튼을 누르면
		
		// 상품정보와 회원정보를 받아와서
		// 주문 상세 페이지에 주문 데이터를 화면에 보여주기
	}
	
	// 상품id를 파라미터로 받는지?
	// 회원id는 authentication에서 추출?
	// 상품옵션은 여러개 있을지 모르니 quantity를 담은 별개의 옵션 객체?
	@PostMapping("/list")
	public void sumbitOrder(Integer memberId, Integer productId, int quantity) {
		// 주문 상세 페이지에서 결제버튼을 누르면
		
		// 회원id, 상품id, 상품옵션을 서비스로 전달
		
		// 주문상세 데이터를 결제페이지로 전송 --> 주문 누르면 결제는 자동완료되었다고 가정
		
	}
	
	@PostMapping("/success")
	public void successOrder() {
		// 결제 완료 시
		
	}
	
}
