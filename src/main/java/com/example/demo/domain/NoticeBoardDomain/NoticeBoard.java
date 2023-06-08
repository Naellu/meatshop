package com.example.demo.domain.NoticeBoardDomain;

import java.time.*;
import java.time.format.*;
import java.util.*;

import lombok.*;

@Data
public class NoticeBoard {
	
	private Integer id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime inserted;
	
	private List<String> fileName;
	
	public String getInserted() {
		return inserted.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
