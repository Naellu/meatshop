package com.example.demo.service.product.review;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.review.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReviewResponseService {
	
	@Autowired
	private ReviewResponseMapper mapper;

	public boolean addResponse(ReviewResponse reviewResponse) {
		int addCheck = mapper.addResponse(reviewResponse);
		return addCheck == 1;
	}

	public boolean removeResponseById(Integer responseId) {
		int removeCheck = mapper.removeResponse(responseId);
		return removeCheck == 1;
	}


	public ReviewResponse getResponse(Integer responseId) {
		return mapper.getResponseByResponseId(responseId);
	}

	public boolean modifyResponse(ReviewResponse reviewResponse) {
		int modifyCheck = mapper.modifyResponse(reviewResponse);
		return modifyCheck == 1;
	}

}
