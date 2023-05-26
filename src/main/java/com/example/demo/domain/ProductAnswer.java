package com.example.demo.domain;

import java.sql.*;

import lombok.*;

@Data
public class ProductAnswer {
	private Integer inquiryId;
	private String answer;
	private Timestamp createdAt;
}
