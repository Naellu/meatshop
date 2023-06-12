package com.example.demo.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.inquiry.*;

@Component
public class CustomerSecurityChecker {

	@Autowired
	private ProductInquiryMapper inquiryMapper;

	public boolean checkInquiryWriter(Authentication authentication, Integer inquiryId) {

		ProductInquiry productInquiry = inquiryMapper.getInquiryByInquiryId(inquiryId);

		String userName = authentication.getName();
		String writer = productInquiry.getCustomerId();

		return userName.equals(writer);
	}

}
