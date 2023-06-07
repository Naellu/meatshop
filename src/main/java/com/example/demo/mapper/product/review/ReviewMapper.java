package com.example.demo.mapper.product.review;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ReviewMapper {

	@Select("""
			SELECT COUNT(*) FROM review
			WHERE 
				product_id = #{productId}
			""")
	Integer size(Review review);

	@Select("""
			SELECT * FROM
			review
			WHERE product_id = #{review.productId}
			ORDER BY
			review_id DESC
			LIMIT 
			#{startIndex}, 10
			""")
	@ResultMap("showListByProductId")
	List<Review> showListByProductId(Review review, Integer startIndex);

	@Insert("""
			INSERT INTO review
			(customer_id, product_id, content, rating)
			VALUES 
			(#{customerId}, #{productId}, #{content}, #{rating})
			""")
	int addReview(Review review);

}
