package com.example.demo.service;

public interface OrderService {
	Long makeOrder(Integer memberId, Integer productId, int quantity);
}
