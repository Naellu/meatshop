package com.example.demo.domain.answer;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Answer {
    private Integer id;
    private Integer questionId;
    private String content;
    private String adminId;
    private LocalDateTime inserted;

}
