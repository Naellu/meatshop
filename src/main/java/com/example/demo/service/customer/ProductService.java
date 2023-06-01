package com.example.demo.service.customer;

import java.util.*;

import com.example.demo.domain.*;

public interface ProductService {
	
	public Map<String, Object> getViewList(Integer page, Integer categoryId, String type, String search);
	
	public ProductView getOneView(Integer id);

	public Map<String, Object> getTopView();
}
