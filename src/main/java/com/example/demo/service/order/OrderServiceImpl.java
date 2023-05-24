package com.example.demo.service.order;

import com.example.demo.domain.Members;
import com.example.demo.domain.Product;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;
import com.example.demo.domain.order.OrderStatus;
import com.example.demo.mapper.order.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderMapper orderMapper;

//	@Override
//	public int makeOrder(String memberId, Integer productId, int quantity) {
//
//		double price = orderMapper.findPrice(productId);
//		int orderPrice = (int) price;
//
//		// 주문상세(OrderItem) 생성 - 상품, 상품가격, 구매수량을 받음
//		OrderItem orderItem = OrderItem.createOrderItem(productId, orderPrice, quantity);
//		orderMapper.saveOrderItems(orderItem);
//
//		// 주문상태와 주문항목을 가진 새로운 주문 생성
//		Order order = Order.createOrder(memberId, orderItem);
//		orderMapper.saveOrder(order);
//
//		return 0;
//	}

	@Override
	public int makeOrder(String memberId, List<Integer> productIdList, List<Integer> quantities) {

		List<OrderItem> orderItems = new ArrayList<>();
		for (int i = 0; i < productIdList.size(); i++) {
			int productId = productIdList.get(i);
			int quantity = quantities.get(i);

			double price = orderMapper.findPrice(productId);
			int orderPrice = (int) price;

			OrderItem orderItem = OrderItem.createOrderItem(productId, orderPrice, quantity);
			orderItems.add(orderItem);
		}

		Order order = Order.createOrder(memberId, orderItems);

		orderMapper.saveOrder(order);

		for (OrderItem orderItem : order.getOrderItems()) {
			orderItem.setOrderId(order.getId());
			orderMapper.saveOrderItems(orderItem);
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


	@Override
	public void orderCancel() {
		// TODO Auto-generated method stub
		//  주문 취소
	}
	
	

}
