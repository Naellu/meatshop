package com.example.demo.service.order;

import java.util.List;

import com.example.demo.domain.order.Order;

public interface OrderService {

	// 주문 하기
//	int makeOrder(String memberId, Integer productId, int quantity);

	int makeOrder(String memberId, List<Integer> productId, List<Integer> quantity);

	// 전체 주문목록
	List<Order> showOrderList(String memberId);
	
	// 주문 취소
	void orderCancel();
	
}
