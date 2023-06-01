package com.example.demo.domain.faqDomain;

import lombok.*;

@Data
public class Faq {

	private Integer id;
	private String title;
	private String category;
	private String content;
	private String writer;
	private String adminId;
}
