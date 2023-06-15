package com.example.demo.mapper.product.review;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ReviewLikeMapper {

	@Delete("""
			DELETE FROM reviewlike
			WHERE 
			customer_id = #{customerId} AND
			review_id = #{reviewId}
			""")
	Integer deleteLike(ReviewLike reviewLike);
	
	
	@Insert("""
			INSERT INTO 
			reviewlike (review_id, customer_id)
			VALUES (#{reviewId}, #{customerId})
			""")
	void insertLike(ReviewLike reviewLike);


	@Select("""
			SELECT COUNT(*)
			FROM reviewlike
			WHERE review_id = #{reviewId}
			""")
	Integer likeCount(ReviewLike reviewLike);

	
	@Select("""
			SELECT COUNT(*)
			FROM reviewlike
			WHERE 
			customer_id = #{customerId}
			AND
			review_id = #{reviewId} 
			""")
	Integer likedByCustomerId(ReviewLike reviewLike);

	
	@Delete("""
			DELETE FROM reviewlike
			WHERE 
			review_id = #{reviewId}
			""")
	void deleteLikesByReviewId(Integer reviewId);

}
