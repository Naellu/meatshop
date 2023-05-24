package com.example.demo.controller.order;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Members;
import com.example.demo.domain.order.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Product;
import com.example.demo.service.order.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@GetMapping("/detail")
	public String getOrderDetail(Model model) {
		
		// 회원이 구매(주문)버튼을 누르면
		
		// 상품정보와 회원정보를 받아와서
		// 주문상세 페이지에 구매할 상품 데이터를 화면에 보여주기

		// 테스트 상품
		Product product01 = new Product();
		product01.setProductId(56);
		product01.setProductName("고기ewqe1");
		product01.setPrice(11111.0);
		product01.setStockQuantity(10);
		product01.setCountryOfOrigin("국산");
		product01.setCategoryId(5);

		Product product02 = new Product();
		product02.setProductId(32);
		product02.setProductName("맛있는ewqtwet 양고기");
		product02.setPrice(330000.0);
		product02.setStockQuantity(3);
		product02.setCountryOfOrigin("호주산");
		product02.setCategoryId(7);
		//

		List<Product> products = new ArrayList();
		products.add(product01);
		products.add(product02);

		// 테스트 회원
		Members member = new Members();
		member.setId("user01");
		member.setMember_password("123");
		member.setMember_email("qwe@qwe.com");
		member.setMember_address("서울");
		member.setMember_created("2023-05-23");
		member.setMember_phone_number("010-1234-1234");

		model.addAttribute("product", products);
		model.addAttribute("member", member);
		model.addAttribute("quantity", 3);
		log.info("model={}",model);
		
		return "order/detail";
	}


//	@PostMapping("/detail")
//	public String submitOrderDetailAsDto(@RequestBody OrderItemDto orderItemDto) {
//
//		// 주문 생성
//		orderService.makeOrderAsDto(orderItemDto.getMemberId(), orderItemDto.getProductIdList(), orderItemDto.getQuantities());
//
//		// 결제페이지로 이동
//		return "order/success";
//	}

	@PostMapping("/detail")
	public String submitOrderDetailAsDto(@RequestParam("memberId") String memberId,
										 @RequestParam("productIdList") List<Integer> productIdList,
										 @RequestParam("quantities") List<Integer> quantities) {

		// 주문 생성
		orderService.makeOrder(memberId, productIdList, quantities);

		// 결제페이지로 이동
		return "order/success";
	}

	// 회원의 주문내역보기
	@GetMapping("/list/{memberId}")
	public void getOrderPage(@PathVariable String memberId, Model model) {
		List<Order> orderList = orderService.showOrderList(memberId);
		model.addAttribute("orderList", orderList);
	}
	
	@PostMapping("/list")
	public void submitOrder() {

	}
	
	@PostMapping("/success")
	public void successOrder() {
		// 결제 완료 시
		
	}
	
}
