package com.example.demo.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.question.*;
import com.example.demo.mapper.question.*;

@Component
public class CustomQuestionChecker {

	@Autowired
	private QuestionMapper questionMapper;

	public boolean checkQuestionId(Authentication authentication, Integer customerId) {
		Question question = questionMapper.selectById(customerId);
		
		String username = authentication.getName();
		String customer = question.getCustomerId();
		
		return username.equals(customer);
	}
}
