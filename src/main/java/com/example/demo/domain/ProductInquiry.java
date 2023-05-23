package com.example.demo.domain;

import java.sql.*;

import lombok.*;

@Data
public class ProductInquiry {
	private Integer inquiryId;
	private Integer productId;
	private String customerName; 
	private String nickName;
	private String inquiryTitle;
	private String inquiryText;
	private Timestamp createdAt;
}
