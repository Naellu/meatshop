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
		Map<String, Object> res = new HashMap<>();
		List<ProductInquiry> productInquiryList = inquiryMapper.showListByProductId(productInquiry, page);
		res.put("productInquiryList", productInquiryList);
		return res;
	}
	
	
	public boolean addInquiry(ProductInquiry productInquiry) {
		productInquiry.setCustomerName(memberMapper.getCustomerNameById(productInquiry.getCustomerId()));
		int cnt = inquiryMapper.addInquiry(productInquiry);
		return cnt == 1;
	}
	
	
	public boolean deleteInquiry(Integer inquiryId) {
		int cnt = inquiryMapper.deleteInquiry(inquiryId);
		return cnt==1;
	}
	
	
	public ProductInquiry getInquiry(Integer inquiryId) {
		
		return inquiryMapper.getInquiryByInquiryId(inquiryId);
	}


	public boolean modifyInquiry(ProductInquiry productInquiry) {
		int cnt = inquiryMapper.modifyInquiry(productInquiry);
		return cnt==1;
	}
	

}
