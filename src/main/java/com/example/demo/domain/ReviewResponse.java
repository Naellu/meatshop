package com.example.demo.domain;

import java.sql.*;

import lombok.*;

@Data
public class ReviewResponse {
	
	private Integer responseId;
	private Integer reviewId;
	private String response;
	private Timestamp createdAt;

}
