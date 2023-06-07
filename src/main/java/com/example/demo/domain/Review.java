package com.example.demo.domain;

import java.sql.*;

import lombok.*;

@Data
public class Review {

	private Integer reviewId;
	private String customerId;
	private Integer productId;
	private String content;
	private Float rating;
	private Timestamp createdAt;
}
