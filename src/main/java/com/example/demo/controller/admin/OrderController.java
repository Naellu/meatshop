package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.order.dto.OrderDtoTest;
import com.example.demo.service.order.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("adminOrderController")
@RequestMapping("/admin/order")
@PreAuthorize("hasAuthority('admin')")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	@GetMapping("/list")
	public String showAllorders(Model model) {
		log.info("order lists IN ADMIN");
		
		List<OrderDtoTest> orders = orderService.showAllOrders();
		model.addAttribute("orderList", orders);
		
		return "admin/order/list";
	}
	
}
