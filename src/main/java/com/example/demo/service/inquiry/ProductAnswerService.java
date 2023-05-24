package com.example.demo.service.inquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.productInquiry.*;

@Service
public class ProductAnswerService {
	
	@Autowired
	private ProductAnswerMapper answerMapper;
	


	public boolean addProductAnswer(ProductAnswer productAnswer) {
		List<Object> answers = answerMapper.getAnswersByInqruiryId(productAnswer.getInquiryId());
		int cnt;
		if (answers.size()==0) {
			cnt = answerMapper.addAnswer(productAnswer);
			
		} else {
			cnt = 0;
		}
		
		return cnt == 1;
	}

	public List<Object> getAnswer(Integer inquiryId) {
		List<Object> answers = answerMapper.getAnswersByInqruiryId(inquiryId);
		return answers;
	}

}
