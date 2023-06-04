package com.example.demo.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;
import com.example.demo.domain.order.Status;
import com.example.demo.domain.order.dto.OrderDto;
import com.example.demo.domain.order.dto.OrderDtoTest;
import com.example.demo.domain.order.dto.OrderItemDto;
import com.example.demo.mapper.order.OrderMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderMapper orderMapper;

	// 단일 상품 주문
	@Override
	public int makeOrderOfSingleProduct(String memberId, Integer productId, int quantity, Double price) {
//		double price = orderMapper.findPrice(productId);
		int orderPrice = price.intValue();

		OrderItem orderItem = OrderItem.createOrderItem(productId, orderPrice, quantity);

		Order order = Order.createOrder(memberId, orderItem);
		orderMapper.saveOrder(order);

		orderItem.setOrderId(order.getId());
		orderMapper.saveOrderItems(orderItem);

		return order.getId();
	}

	@Override
	@Transactional
	public int makeOrderOfMultipleProduct(String memberId, List<OrderItemDto> orderItemDtos) {
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(OrderItemDto orderItemDto : orderItemDtos) {
			OrderItem orderItem = OrderItem.createOrderItem(
					orderItemDto.getProductId(), 
					orderItemDto.getPrice().intValue(),
					orderItemDto.getQuantity());
			orderItems.add(orderItem);
		}
		
		
		Order order = Order.createOrder(memberId, orderItems);
		orderMapper.saveOrder(order);
		
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(order.getId());
			orderMapper.saveOrderItems(orderItem);
		}
		
		List<Integer> productIds = orderItemDtos.stream()
				.filter(OrderItemDto::isFromCart)
				.map(OrderItemDto::getProductId)
				.collect(Collectors.toList());
		
		if(!productIds.isEmpty()) {
			// 장바구니에서 상품을 주문했다면 장바구니에서 항목 삭제
			orderMapper.deleteItemsFromCart(productIds, memberId);
		}
		
		return order.getId();
	}

	// 특정 회원 주문목록 보기
	@Override
	public List<OrderDto> showOrderList(String memberId) {
		List<OrderDto> orderItemList = orderMapper.findAllByMemberId(memberId);
		return orderItemList;
	}

	// 전체 회원 주문목록 보기
	public List<OrderDtoTest> showAllOrders() {
		List<OrderDtoTest> orders = orderMapper.findAllOrders();
		return orders;
	}

	@Override
	public String findOneOfProduct(Integer productId) {
		return orderMapper.selectByProductId(productId);
	}

	// 주문 취소
	@Override
	public void cancel(Integer orderId) {
		OrderDto order = orderMapper.findById(orderId);
		log.info("order IN SERVICE={}", order);
		if(order.getStatus().equals(Status.CREATED.name())) {
			orderMapper.cancel(orderId, Status.CANCEL.name());
		} else {
			throw new IllegalStateException("주문상태가 CREATED가 아닙니다");
		}
	}

	

	
	
	

}
