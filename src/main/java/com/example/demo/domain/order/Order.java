package com.example.demo.domain.order;

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
	private LocalDateTime created;
	private Status status; // 주문상태
	private Long totalPrice;

	public Order(String memberId) {
		this.memberId = memberId;
	}

	// 주문에 주문상세 추가
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

	// 단일 상품 주문생성
	public static Order createOrder(String memberId, OrderItem orderItem) {
		Order order = new Order();
		order.setMemberId(memberId);
		order.setStatus(Status.CREATED); // 주문생성 시 결제상태는 무조건 CREATED(결제전)
		order.addOrderItem(orderItem);
		order.calculateTotalPrice();
		return order;
	}

	// 여러개의 주문상세와 하나의 회원을 가진 주문 객체 생성하기
	public static Order createOrder(String memberId, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setMemberId(memberId);
		order.setStatus(Status.CREATED); // 주문생성 시 결제상태는 무조건 CREATED(결제전)
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.calculateTotalPrice();
		return order;
	}

	// 주문 전체 가격
	public void calculateTotalPrice() {
		this.totalPrice = this.orderItems.stream()
				.mapToLong(orderItem -> orderItem.getOrderPrice() * orderItem.getQuantity())
				.sum();
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", memberId='" + memberId + '\'' +
				", orderItems=" + orderItems +
				", status=" + status +
				", totalPrice=" + totalPrice +
				'}';
	}
}
