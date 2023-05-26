package com.example.demo.service.order;

import com.example.demo.domain.Product;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;
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
	public int makeOrderOfMultipleProduct(String memberId, List<Integer> productIdList, List<Integer> quantities) {

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

	public List<Order> showAllOrders() {
		List<Order> orders = orderMapper.findAll();
		return orders;
	}

	@Override
	public Product findOneOfProduct(Integer productId) {
		return orderMapper.selectByProductId(productId);
	}

	@Override
	public void orderCancel() {
		// TODO Auto-generated method stub
		//  주문 취소
	}
	
	

}
