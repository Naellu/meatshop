package com.example.demo.service.inquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.productInquiry.*;

@Service
public class ProductInquiryService {

	@Autowired
	private ProductInquiryMapper mapper;
	
	
	
	public Map<String, Object> showInquiryListByProductId(Integer productId) {
		List<ProductInquiry> productInquiryList = mapper.showListByProductId(productId);		
		return Map.of("productInquiryList", productInquiryList);
	}
	
	
	public boolean addInquiry(ProductInquiry productInquiry) {
		int cnt = mapper.addInquiry(productInquiry);
		return cnt == 1;
	}
	
	
	public boolean deleteInquiry(Integer inquiryId) {
		System.out.println(inquiryId);
		int cnt = mapper.deleteInquiry(inquiryId);
		return cnt==1;
	}
	
	
	public ProductInquiry getInquiry(Integer inquiryId) {
		return mapper.getInquiryByInquiryId(inquiryId);
	}


	public boolean modifyInquiry(ProductInquiry productInquiry) {
		System.out.println(productInquiry);
		int cnt = mapper.modifyInquiry(productInquiry);
		return cnt==1;
	}
	

}
