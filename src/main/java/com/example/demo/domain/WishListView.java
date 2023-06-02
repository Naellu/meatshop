package com.example.demo.domain;

import java.util.*;

import lombok.*;

@Data
public class WishListView {
	private Integer id;
	private String memberId;
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
	
}
