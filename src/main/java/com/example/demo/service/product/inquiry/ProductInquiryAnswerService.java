package com.example.demo.service.product.inquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.inquiry.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInquiryAnswerService {

	@Autowired
	private ProductAnswerMapper answerMapper;

	public boolean addProductAnswer(ProductAnswer productAnswer) {
		ProductAnswer answer = answerMapper.getAnswersByInqruiryId(productAnswer.getInquiryId());
		int cnt;
		if (answer == null) {
			cnt = answerMapper.addAnswer(productAnswer);

		} else {
			cnt = 0;
		}

		return cnt == 1;
	}

	public ProductAnswer getAnswer(Integer inquiryId) {
		return answerMapper.getAnswersByInqruiryId(inquiryId);
	}

	public boolean modifyAnswer(ProductAnswer productAnswer) {
		int cnt = answerMapper.modifyAnswerByInquiryId(productAnswer);

		return cnt == 1;
	}

	public Map<String, Object> remove(Integer inquiryid) {
		Map<String, Object> res = new HashMap<>();
		int cnt = answerMapper.removeAnswerByInquiryId(inquiryid);
		
		if(cnt==1) {
			res.put("message", "답변이 삭제되었습니다.");
		} else {
			res.put("message", "답변이 삭제되지 않았습니다.");
		}
		return res;
	}

}
