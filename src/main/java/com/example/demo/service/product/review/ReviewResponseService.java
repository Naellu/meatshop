package com.example.demo.service.product.review;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.review.*;

@Service
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

}
