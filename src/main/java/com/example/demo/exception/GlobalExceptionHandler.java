package com.example.demo.exception;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(UpdateException ex) {
        // 예외 처리 로직 구현
        return "redirect:/"; // 에러 페이지로 리디렉션
    }
}