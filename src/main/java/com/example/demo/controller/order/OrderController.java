package com.example.demo.controller.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.order.Status;
import com.example.demo.domain.order.dto.OrderDto;
import com.example.demo.domain.order.dto.OrderItemDto;
import com.example.demo.domain.payment.PaymentDto;
import com.example.demo.exception.NotEnoughStockException;
import com.example.demo.service.order.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	// 상품 상세에서 order/detail POST 요청 처리하는 메서드
	@PostMapping("/detail")
	public ResponseEntity<List<OrderItemDto>> initOrderUseJSON(@RequestBody List<OrderItemDto> orderItemDtos, HttpSession session, Authentication authentication) {
		// 회원 인증정보 확인
		if(authentication == null || !authentication.isAuthenticated() ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		session.setAttribute("orderItemDtos", orderItemDtos);
		
		if(orderItemDtos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(orderItemDtos);
		}
	}

	
	@GetMapping("/detail")
	@PreAuthorize("isAuthenticated()")
	public String checkOrderDetail(HttpSession session, Authentication authentication, Model model) {
		
		List<OrderItemDto> orderItemDtos = (List<OrderItemDto>) session.getAttribute("orderItemDtos");
		List<String> ProductNames = new ArrayList();
		
		for(OrderItemDto orderItemDto : orderItemDtos) {
			Integer productId = orderItemDto.getProductId();
			
			String memberId = authentication.getName();
			orderItemDto.setMemberId(memberId);

			String productName = orderService.findOneOfProduct(productId);
			ProductNames.add(productName);
		}
		
		model.addAttribute("productNames", ProductNames);
		model.addAttribute("orderItemDtos", orderItemDtos);
		
		session.removeAttribute("orderItemDtos");
		
		return "order/detail";
	}
	
	
	// 주문 상세에서 결제버튼 누르면 
	// 실제 주문 들어가는 POST 메서드
	@PostMapping("/payed")
	@PreAuthorize("isAuthenticated()")
	public PaymentDto payedOrder(@RequestBody List<OrderItemDto> orderItemDtos, Authentication authentication) throws NotEnoughStockException {
		String memberId = authentication.getName();
		int orderId = orderService.makeOrderOfMultipleProduct(memberId, orderItemDtos);
		log.info("orderId IN order/payed CONTROLLER ={}",orderId);
		
		// 결제 함수인 requestPay()에 필요한 데이터가 담긴 paymentDto를 보낼 예정
		PaymentDto paymentDto = orderService.findRequiredPaymentData(orderId);
		log.info("PaymentDto={}",paymentDto);
		return paymentDto;
	}

	// 회원의 주문내역보기
	@GetMapping("/list/{memberId}")
	public String getOrderPage(@PathVariable String memberId, Model model) {
		List<OrderDto> orderList = orderService.showOrderList(memberId);
		
		Map<String, Integer> statusCount = new HashMap();
		List<String> titleArray = Status.fromName(Status.values());
		
		for(String title : titleArray) {
			statusCount.put(title, 0);
		}
		
		
		for (OrderDto order : orderList) {
			String status = order.getStatus().getTitle();
			statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
		}
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("statusCount", statusCount);
		log.info("orderList={}",orderList);
		log.info("statusCount={}",statusCount);
		return "order/list";
	}

	@GetMapping("/success")
	public void successOrder() {
		// 결제 완료 시
		
	}
	
	// 주문 취소
	@PostMapping("/cancel")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String cancelOrder(@RequestBody OrderDto orderDto) {
		orderService.cancel(orderDto.getId());
		return "주문이 취소되었습니다";
	}
	
}
