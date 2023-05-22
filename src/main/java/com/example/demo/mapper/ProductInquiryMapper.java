package com.example.demo.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ProductInquiryMapper {

	@Select("""
			SELECT * FROM
			productinquiry
			WHERE product_id = #{id}
			""")
	List<ProductInquiry> showListByProductId(Integer id);

}
