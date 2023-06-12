package com.example.demo.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Product;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;
import com.example.demo.domain.order.Status;
import com.example.demo.domain.order.dto.OrderDto;
import com.example.demo.domain.order.dto.OrderItemDto;
import com.example.demo.exception.NotEnoughStockException;
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
	public int makeOrderOfMultipleProduct(String memberId, List<OrderItemDto> orderItemDtos) throws NotEnoughStockException {
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(OrderItemDto orderItemDto : orderItemDtos) {
			OrderItem orderItem = OrderItem.createOrderItem(
					orderItemDto.getProductId(), 
					orderItemDto.getPrice().intValue(),
					orderItemDto.getQuantity());
			orderItems.add(orderItem);

			// 주문 전에 재고수량 감소
			Product product = orderMapper.selectAllByProductId(orderItem.getProductId());
			product.removeStock(orderItem.getQuantity());
			orderMapper.updateProductQuantity(product.getProductId(), product.getStockQuantity());
		}
		
		
		Order order = Order.createOrder(memberId, orderItems);
		log.info("order Status IN SERVICE = {}", order.getStatus());
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

	// 관리자 전체 회원 주문목록 보기
	@Override
	public Map<String, Object> showAllOrders(Integer page, String search, String type) {
		// 페이지당 행의 수
		Integer rowPerPage = 10;

		// 쿼리 LIMIT 절에 사용할 시작 인덱스
		Integer startIndex = (page - 1) * rowPerPage;

		// 페이지네이션이 필요한 정보
		// 전체 레코드 수
		Integer numOfRecords = orderMapper.countAll(search, type);
		// 마지막 페이지 번호
		Integer lastPageNumber = (numOfRecords - 1) / rowPerPage + 1;
		// 페이지네이션 왼쪽번호
		Integer leftPageNum = page - 5;
		// 1보다 작을 수 없음
		leftPageNum = Math.max(leftPageNum, 1);

		// 페이지네이션 오른쪽번호
		Integer rightPageNum = leftPageNum + 9;
		// 마지막페이지보다 클 수 없음
		rightPageNum = Math.min(rightPageNum, lastPageNumber);

		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("rightPageNum", rightPageNum);
		pageInfo.put("leftPageNum", leftPageNum);
		pageInfo.put("currentPageNum", page);
		pageInfo.put("lastPageNum", lastPageNumber);
		
		List<OrderDto> orders = orderMapper.findAllOrders(startIndex, rowPerPage, search, type);
		log.info("orders size IN SERVICE = {}",orders.size());
		log.info("orders info IN SERVICE = {}",orders);
		
		return Map.of("pageInfo", pageInfo, 
				"orderList", orders);
	}
	
	@Override
	public List<OrderDto> findAllOrders() {
		return orderMapper.findAll();
	}

	@Override
	public String findOneOfProduct(Integer productId) {
		return orderMapper.selectByProductId(productId);
	}

	// 주문 취소
	@Override
	public String cancel(Integer orderId) {
		OrderDto order = orderMapper.findById(orderId);
		if(order.getStatus().equals(Status.CREATED)) {
			log.info("Status update is={}",orderMapper.updateStatus(orderId, Status.CANCEL));
			
			// 취소 시 주문한 상품들의 재고수량 증가
			List<OrderItem> orderItems = orderMapper.findAllOrderItemByOrderId(orderId);
			for(OrderItem orderItem : orderItems) {
				Product product = orderMapper.selectAllByProductId(orderItem.getProductId());
				product.addStock(orderItem.getQuantity());
				orderMapper.updateProductQuantity(product.getProductId(), product.getStockQuantity());
			}
			return "주문을 취소 하였습니다";
			
		} else {
			return "주문을 취소할 수 있는 상태가 아닙니다";
		}
	}

	// 관리자의 주문 상태 변경
	@Override
	public boolean updateStatus(Integer orderId, Status status) {
		return orderMapper.updateStatus(orderId, status);
	}

	

	
	
	

}
