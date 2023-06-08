package com.example.demo.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.order.Status;
import com.example.demo.domain.order.dto.OrderDto;
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
	public String showAllorders(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "type", required = false) String type,
			Model model) {
		
		Map<String, Object> result = orderService.showAllOrders(page, search, type);
		List<OrderDto> orderList = orderService.findAllOrders();
		
		Map<String, Integer> statusCount = new HashMap();
		List<String> titleArray = Status.fromName(Status.values());
		
		for(String title : titleArray) {
			statusCount.put(title, 0);
		}
		for (OrderDto order : orderList) {
			String status = order.getStatus().getTitle();
			statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
		}
		
		model.addAllAttributes(result);
		model.addAttribute("statusCount", statusCount);
		
		return "admin/order/list";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String updateStatus(@RequestBody OrderDto orderDto) {
		log.info("orderDto IN ADMINCONTROLLER={}",orderDto);
		boolean isUpdateStatus = orderService.updateStatus(orderDto.getId(), orderDto.getStatus());
		
		if(isUpdateStatus) {
			return "주문 상태가 변경되었습니다";
		} else {
			return "주문 상태를 변경하지 못하였습니다";
		}
	}
	
	@PostMapping("/cancel")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String cancelOrder(@RequestBody OrderDto orderDto) {
		orderService.cancel(orderDto.getId());
		return "주문을 취소 하였습니다";
	}
	
	
}
