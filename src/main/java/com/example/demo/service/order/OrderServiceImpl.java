package com.example.demo.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;
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

	public boolean payingOrder() {
		// TODO Auto-generated method stub
		//  결제 완료
		return true;
	}
	
	


	@Override
	public List<Order> showOrderList(String memberId) {
		List<Order> orderItemList = orderMapper.findAllByMemberId(memberId);
		return orderItemList;
	}

	// 전체 주문목록 보기
	public List<Order> showAllOrders() {
		List<Order> orders = orderMapper.findAll();
		return orders;
	}

	@Override
	public String findOneOfProduct(Integer productId) {
		return orderMapper.selectByProductId(productId);
	}

	@Override
	public void orderCancel() {
		// TODO Auto-generated method stub
		//  주문 취소
	}

	

	
	
	

}
