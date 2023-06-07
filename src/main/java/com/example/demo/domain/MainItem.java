package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
public class MainItem {
	private Integer productId;
	private String productName;
	private String countryOfOrigin;
	private Double price;
	private Integer stockQuantity;
	private Integer categoryId;
	private String categoryName;
	private Integer pub;
	
	private Integer sold;
}
