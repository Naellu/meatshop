package com.example.demo.exception;

import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TransactionException.class)
	public String handleException(TransactionException ex, Model model) {
		// 예외 처리 로직 구현
		String message = ex.getMessage();
		model.addAttribute("message", message);
		return "exception/error"; // 에러 페이지로 리디렉션
	}
}