package com.example.demo.domain;

import java.util.*;

import lombok.*;

@Getter
@Setter
@ToString
public class ProductView {
	private Integer productId;
	private String productName;
	private String countryOfOrigin;
	private Double price;
	private Integer stockQuantity;
	private Integer categoryId;
	private String categoryName;
	private String description;
	private Integer pub;
	
	private List<String> fileName;
	
	private Boolean liked;
}
