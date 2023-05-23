package com.example.demo.service.admin;

import java.util.*;

import com.example.demo.domain.*;

public interface AdminProductService {

	public List<ProductView> getViewList();

	public ProductView getOneView(Integer productId);

	public Boolean remove(Integer productId);
}
