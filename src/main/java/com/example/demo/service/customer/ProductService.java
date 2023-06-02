package com.example.demo.service.customer;

import java.util.*;

import org.springframework.security.core.*;

import com.example.demo.domain.*;

public interface ProductService {
	
	public Map<String, Object> getViewList(Integer page, Integer categoryId, String type, String search, Authentication authentication);
	
	public ProductView getOneView(Integer id);

	public Map<String, Object> getTopView();
}
