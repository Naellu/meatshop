package com.example.demo.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

	@Override
	public Long makeOrder(Integer memberId, Integer productId, int quantity) {
		
		// 회원id로 회원정보 찾기
		// 상품id로 상품정보 찾기
		
		// 주문상세(OrderItem) 생성 - 상품객체, 상품 총 가격, 수량을 받음
		
		// 주문(Order) 생성 - 회원객체, 주문상세객체를 받음
		
		// 결제 완료시
			// 주문테이블에 주문 저장
		
		return null;
	}

}
