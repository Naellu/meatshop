package com.example.demo.domain;

import java.sql.*;

import lombok.*;

@Data
public class ProductInquiry {
	private Integer id;
	private Integer product_id;
	private String customer_name; 
	private String nickname;
	private String inquiry_title;
	private String inquiry_text;
	private Timestamp created_at;
}
