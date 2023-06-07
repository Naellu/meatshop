package com.example.demo.service.product.review;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.review.*;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewMapper reviewMapper;

	public Map<String, Object> showReviewListByProductId(Review review, Integer page) {


		Integer startIndex = (page - 1) * 10;

		Integer size = reviewMapper.size(review);

		Integer leftPageNumber = Math.max(1, page - 5);
		Integer rightPageNumber = Math.min(size, page + 5);

		// 이전버튼 페이지 번호 구하기
		Integer prevPageNumber = Math.max(1, page - 1);
		Integer nextPageNumber = Math.min(size, page + 1);

		// 마지막 페이지 구하기
		Integer lastPageNumber = (size - 1) / 10 + 1;
		
		rightPageNumber = Math.min(rightPageNumber, lastPageNumber);

		Map<String, Object> pageInfo = new HashMap<>();

		pageInfo.put("begin", leftPageNumber);
		pageInfo.put("end", rightPageNumber);
		pageInfo.put("prevPageNumber", prevPageNumber);
		pageInfo.put("nextPageNumber", nextPageNumber);
		pageInfo.put("lastPageNumber", lastPageNumber);
		pageInfo.put("currentPageNumber", page);
		pageInfo.put("num", 10);


		List<Review> reviewList = reviewMapper.showListByProductId(review, startIndex);
		
		Map<String, Object> reviewInfo = new HashMap<>();
		reviewInfo.put("productId", review.getProductId());
		reviewInfo.put("customerId", review.getCustomerId());
		
		Map<String, Object> res = new HashMap<>();
		res.put("reviewList", reviewList);
		res.put("pageInfo", pageInfo);
		res.put("reviewInfo", reviewInfo);
		return res;
	}
	
	


	public boolean addReview(Review review) {
		int check = reviewMapper.addReview(review);
		return check == 1;
	}

}
