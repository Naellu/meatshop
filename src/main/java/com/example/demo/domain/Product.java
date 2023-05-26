package com.example.demo.domain;

import com.example.demo.exception.*;

import lombok.*;

@Data
public class Product {
	private Integer productId;
	private String productName;
	private String countryOfOrigin;
	private Integer categoryId;
	private Double price;
	private Integer stockQuantity;
	private Integer pub;

	// ==비즈니스 로직==//
	/**
	 * stock 증가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/**
	 * stock 감소
	 * 
	 * @throws NotEnoughStockException
	 */
	public void removeStock(int quantity) throws NotEnoughStockException {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
}
