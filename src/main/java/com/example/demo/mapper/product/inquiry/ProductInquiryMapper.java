package com.example.demo.mapper.product.inquiry;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ProductInquiryMapper {

	@Select("""
			SELECT * FROM
			productinquiry
			WHERE product_id = #{productInquiry.productId}
			ORDER BY
			inquiry_id DESC
			LIMIT 
			#{startIndex}, 10
			""")
	@ResultMap("showListByProductId")
	List<ProductInquiry> showListByProductId(ProductInquiry productInquiry, Integer startIndex);

	@Insert("""
			INSERT INTO	productinquiry(product_id,customer_name,customer_id,inquiry_title,inquiry_text)
			VALUES(#{productId},#{customerName},#{customerId},#{inquiryTitle},#{inquiryText})
			""")
	int addInquiry(ProductInquiry productInquiry);

	@Delete("""
			DELETE
			FROM productinquiry
			WHERE inquiry_id = #{inquiryId }
			""")
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
	int modifyInquiry(ProductInquiry productInquiry);

	@Select("""
			SELECT COUNT(*) FROM productinquiry
			WHERE 
				product_id = #{productId}
			""")
	Integer size(ProductInquiry productInquiry);


}
