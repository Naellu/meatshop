package com.example.demo.service.order;

import java.util.List;

import com.example.demo.domain.order.dto.OrderDto;
import com.example.demo.domain.order.dto.OrderDtoTest;
import com.example.demo.domain.order.dto.OrderItemDto;

public interface OrderService {

	// 단일 상품 주문
	int makeOrderOfSingleProduct(String memberId, Integer productId, int quantity, Double price);

	// 여러 상품 주문
	int makeOrderOfMultipleProduct(String memberId, List<OrderItemDto> orderItemDtos);

	// 회원 주문목록
	List<OrderDto> showOrderList(String memberId);

	// 전체 주문목록
	List<OrderDtoTest> showAllOrders();

	// 상품 이름 찾기
	String findOneOfProduct(Integer productId);

	// 주문 취소
	void cancel(Integer orderId);
	
}
