package com.example.demo.domain.answer;

import java.time.*;

import lombok.*;

@Data
public class Answer {

	private Integer id;
	private Integer questionId;
    private String content;
    private String adminId;
    private LocalDateTime inserted;
    
    private Boolean editable;
}
