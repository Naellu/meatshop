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

	@Insert("""
			INSERT INTO	productinquiry(product_id,customer_name,nickname,inquiry_title,	inquiry_text)
			VALUES(#{productId},'user1',#{userNickname},#{inquiryTitle},	#{inquiryText})
			""")
	int addInquiry(Integer productId, String userNickname, String inquiryTitle, String inquiryText);


}
