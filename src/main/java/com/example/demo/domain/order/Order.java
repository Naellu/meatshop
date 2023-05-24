package com.example.demo.domain.order;

import com.example.demo.domain.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {
	private Integer id;
	private String memberId;
	private List<OrderItem> orderItems = new ArrayList<>();
	private OrderStatus orderStatus; // 주문상태
	private Long totalPrice;

	public Order(String memberId) {
		this.memberId = memberId;
	}

	// 주문에 주문상세 추가
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

	// 여러개의 주문상세와 하나의 회원을 가진 주문 객체 생성하기
	public static Order createOrder(String memberId, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setMemberId(memberId);
		order.setOrderStatus(OrderStatus.CREATED); // 주문생성 시 결제상태는 무조건 CREATED(결제전)
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.calculateTotalPrice();
		return order;
	}

	// 주문 전체 가격
	public void calculateTotalPrice() {
		this.totalPrice = this.orderItems.stream()
				.mapToLong(orderItem -> orderItem.getOrderPrice())
				.sum();
	}
}
