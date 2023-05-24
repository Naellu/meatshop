package com.example.demo.domain;

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
}
