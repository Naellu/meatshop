package com.example.demo.controller.order;

import com.example.demo.domain.Product;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.dto.OrderItemDto;
import com.example.demo.service.order.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	// 상품 상세에서 order/detail POST 요청 처리하는 메서드
	@PostMapping("/detail")
	@ResponseBody
	public List<OrderItemDto> initOrderUseJSON(@RequestBody List<OrderItemDto> orderItemDtos, HttpSession session) {
	    // JSON 데이터 세션에 저장
	    session.setAttribute("orderItemDtos", orderItemDtos);
	    // OrderItemDto의 리스트를 GET 메서드에 반환해줘야함
	    return orderItemDtos;
	}

	// 상품 상세에서 데이터 받아서 주문 상세 보여주기
	@GetMapping("/detail")
	public String checkOrderDetail(HttpSession session, Model model) {
		
		List<OrderItemDto> orderItemDtos = (List<OrderItemDto>) session.getAttribute("orderItemDtos");
		List<String> ProductNames = new ArrayList();
		
		for(OrderItemDto orderItemDto : orderItemDtos) {
			Integer productId = orderItemDto.getProductId();
			
			String memberId = "user22"; // 임시 회원id
			orderItemDto.setMemberId(memberId);

			String productName = orderService.findOneOfProduct(productId);
			ProductNames.add(productName);
		}
		
		model.addAttribute("productNames", ProductNames);
		model.addAttribute("orderItemDtos", orderItemDtos);
		
		return "order/detail";
	}
	
	// 주문 상세에서 결제버튼 누르면 
	// 실제 주문 들어가는 POST 메서드
	@PostMapping("/payed")
	public ResponseEntity payedOrder(@RequestBody List<OrderItemDto> orderItemDtos) {
		
		String memberId = "user22"; // Authentication으로 받아와야 하는 회원id
		// String memberId = authentication.getName();
		
		int orderId = orderService.makeOrderOfMultipleProduct(memberId, orderItemDtos);
		
		return ResponseEntity.ok(orderId);
		
	}

	// 전체 주문내역보기
	@GetMapping("/list")
	public String submitOrder(Model model) {
		List<Order> orders = orderService.showAllOrders();
		model.addAttribute("orders", orders);
		log.info("model={}", model);
		return "order/list";
	}

	// 회원의 주문내역보기
	// @GetMapping("/list/{memberId}")
	// public void getOrderPage(@PathVariable String memberId, Model model) {
	// List<Order> orderList = orderService.showOrderList(memberId);
	// model.addAttribute("orderList", orderList);
	// }

	@GetMapping("/success")
	public void successOrder() {
		// 결제 완료 시

	}

}
