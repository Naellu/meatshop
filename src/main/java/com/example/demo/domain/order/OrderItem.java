package com.example.demo.domain.order;

import java.util.List;

import com.example.demo.domain.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {
	private Integer id;
	private Integer productId;
	private Integer orderId;
	private Integer quantity; // 상품 주문 수량
	private int orderPrice; // 주문 가격

	// 주문상세 생성하기
	public static OrderItem createOrderItem(Integer productId, int orderPrice, int quantity) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProductId(productId);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setQuantity(quantity);
		return orderItem;
	}
	
}
