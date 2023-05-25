package com.example.demo.mapper.customer;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface CustomerProductMapper {
	// 상품 목록
	public List<ProductView> getAllView();

	public ProductView getViewById(Integer id);
}
