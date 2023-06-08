package com.example.demo.domain.answer;

import java.time.LocalDateTime;
import java.time.format.*;

import lombok.Data;

@Data
public class Answer {
    private Integer id;
    private Integer questionId;
    private String content;
    private String adminId;
    private LocalDateTime inserted;

    private Boolean isAdmin;
    public String getInserted() {
		return inserted.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
