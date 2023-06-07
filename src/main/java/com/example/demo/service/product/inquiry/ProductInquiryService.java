package com.example.demo.service.product.inquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;
import com.example.demo.mapper.product.inquiry.*;

@Service
public class ProductInquiryService {

	@Autowired
	private ProductInquiryMapper inquiryMapper;

	@Autowired
	private MemberMapper memberMapper;

	public Map<String, Object> showInquiryListByProductId(ProductInquiry productInquiry, Integer page) {


		Integer startIndex = (page - 1) * 10;

		System.out.println(productInquiry.getProductId());
		Integer size = inquiryMapper.size(productInquiry);
		
		System.out.println("size:" + size);

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


		List<ProductInquiry> productInquiryList = inquiryMapper.showListByProductId(productInquiry, startIndex);
		
		Map<String, Object> inquiryInfo = new HashMap<>();
		inquiryInfo.put("productId", productInquiry.getProductId());
		inquiryInfo.put("customerId", productInquiry.getCustomerId());
		
		Map<String, Object> res = new HashMap<>();
		res.put("productInquiryList", productInquiryList);
		res.put("pageInfo", pageInfo);
		res.put("inquiryInfo", inquiryInfo);
		
		System.out.println(res.get("pageInfo"));
		
		return res;
	}

	public boolean addInquiry(ProductInquiry productInquiry) {
		productInquiry.setCustomerName(memberMapper.getCustomerNameById(productInquiry.getCustomerId()));
		int cnt = inquiryMapper.addInquiry(productInquiry);
		return cnt == 1;
	}

	public boolean deleteInquiry(Integer inquiryId) {
		int cnt = inquiryMapper.deleteInquiry(inquiryId);
		return cnt == 1;
	}

	public ProductInquiry getInquiry(Integer inquiryId) {

		return inquiryMapper.getInquiryByInquiryId(inquiryId);
	}

	public boolean modifyInquiry(ProductInquiry productInquiry) {
		int cnt = inquiryMapper.modifyInquiry(productInquiry);
		return cnt == 1;
	}

}
