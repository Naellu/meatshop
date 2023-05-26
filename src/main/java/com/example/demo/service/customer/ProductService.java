package com.example.demo.service.customer;

import java.util.*;

import com.example.demo.domain.*;

public interface ProductService {
	
	public List<ProductView> getViewList();
	
	public ProductView getOneView(Integer id);

}
