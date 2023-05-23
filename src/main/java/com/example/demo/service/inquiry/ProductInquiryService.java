package com.example.demo.service.inquiry;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;
import com.example.demo.mapper.productInquiry.*;

@Service
public class ProductInquiryService {

	@Autowired
	private ProductInquiryMapper mapper;
	public Map<String, Object> showInquiryList(Integer id) {
		
		List<ProductInquiry> inquiryList = mapper.showListByProductId(id);		
		
		return Map.of("inquiryList", inquiryList);
	}
	public boolean addInquiry(Integer productId, String userNickname, String inquiryTitle, String inquiryText) {
		int cnt = mapper.addInquiry(productId, userNickname, inquiryTitle, inquiryText);
		
		return cnt == 1;
	}
	public boolean deleteInquiry(Integer inquiryId) {
		int cnt = mapper.deleteInquiry(inquiryId);
		return cnt==1;
	}

}
