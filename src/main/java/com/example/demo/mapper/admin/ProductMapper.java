package com.example.demo.mapper.admin;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ProductMapper {

	public List<ProductView> getAllView();

	public ProductView getViewById(Integer productId);

	public Integer deleteById(Integer productId);
}
