package com.example.demo.service.product.inquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.inquiry.*;

@Service
public class ProductInquiryService {

	@Autowired
	private ProductInquiryMapper mapper;
	
	
	
	public Map<String, Object> showInquiryListByProductId(Integer productId, Integer page) {
		Map<String, Object> res = new HashMap<>();
		List<ProductInquiry> productInquiryList = mapper.showListByProductId(productId, page);
		res.put("productInquiryList", productInquiryList);
		return res;
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
