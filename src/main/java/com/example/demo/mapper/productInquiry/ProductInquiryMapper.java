package com.example.demo.mapper.productInquiry;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ProductInquiryMapper {

	@Select("""
			SELECT * FROM
			productinquiry
			WHERE product_id = #{productId}
			LIMIT 10
			""")
	@ResultMap("showListByProductId")
	List<ProductInquiry> showListByProductId(Integer productId, Integer inquirys);

	@Insert("""
			INSERT INTO	productinquiry(product_id,customer_name,customer_id,inquiry_title,	inquiry_text)
			VALUES(#{productId},'user1',#{customerId},#{inquiryTitle},#{inquiryText})
			""")
	@ResultMap("addInquiry")
	int addInquiry(ProductInquiry productInquiry);

	@Delete("""
			DELETE
			FROM productinquiry
			WHERE inquiry_id = #{inquiryId }
			""")
	@ResultMap("deleteInquiry")
	int deleteInquiry(Integer inquiryId);

	@Select("""
			SELECT * FROM
			productinquiry
			WHERE inquiry_id = #{inquiryId}
			""")
	@ResultMap("getInquiryByInquiryId")
	ProductInquiry getInquiryByInquiryId(Integer inquiryId);
	
	
	@Update("""
			UPDATE productinquiry
			SET 
			inquiry_title = #{inquiryTitle},
			inquiry_text = #{inquiryText}
			WHERE 
			inquiry_id = #{inquiryId}
			""")
	@ResultMap("modifyInquiry")
	int modifyInquiry(ProductInquiry productInquiry);


}
