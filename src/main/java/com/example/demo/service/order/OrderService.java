package com.example.demo.service.order;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.order.Status;
import com.example.demo.domain.order.dto.OrderDto;
import com.example.demo.domain.order.dto.OrderItemDto;
import com.example.demo.exception.NotEnoughStockException;

public interface OrderService {

	// 단일 상품 주문
	int makeOrderOfSingleProduct(String memberId, Integer productId, int quantity, Double price);

	// 여러 상품 주문
	int makeOrderOfMultipleProduct(String memberId, List<OrderItemDto> orderItemDtos) throws NotEnoughStockException;

	// 회원 주문목록
	List<OrderDto> showOrderList(String memberId);

	// 전체 주문목록
	Map<String, Object> showAllOrders(Integer page, String search, String type);
	
	// 전체 주문목록(조건X)
	List<OrderDto> findAllOrders();

	// 상품 이름 찾기
	String findOneOfProduct(Integer productId);

	// 주문 취소
	void cancel(Integer orderId);
	
	// 주문 상태 변경
	boolean updateStatus(Integer orderId, Status status);
}
