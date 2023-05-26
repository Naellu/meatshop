package com.example.demo.service.order;

import java.util.List;

import com.example.demo.domain.order.Order;

public interface OrderService {

	// 단일 상품 주문
	int makeOrderOfSingleProduct(String memberId, Integer productId, int quantity);

	// 여러 상품 주문
	int makeOrderOfMultipleProduct(String memberId, List<Integer> productId, List<Integer> quantity);

	// 회원 주문목록
	List<Order> showOrderList(String memberId);

	// 전체 주문목록
	List<Order> showAllOrders();

	// 주문 취소
	void orderCancel();
	
}
