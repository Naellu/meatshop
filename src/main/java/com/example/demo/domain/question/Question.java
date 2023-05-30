package com.example.demo.domain.question;

import java.time.*;
import java.util.*;

import lombok.*;

@Data
public class Question {

	private Integer id;
	private String customerId;
	private String title;
	private String content;
	private LocalDateTime inserted;
	private Boolean answered;
	
	private List<String> fileName;
}
