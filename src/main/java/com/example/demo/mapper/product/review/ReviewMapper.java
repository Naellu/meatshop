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
			SELECT *
			FROM review r
			INNER JOIN (
				SELECT DISTINCT review_id
				FROM review
				WHERE product_id = #{review.productId}
				ORDER BY review_id DESC
				LIMIT #{startIndex}, 10
			) 
			AS subquery ON r.review_id = subquery.review_id
			LEFT JOIN reviewfile rf ON r.review_id = rf.review_id
			LEFT JOIN reviewresponse rr ON r.review_id = rr.review_id
			ORDER BY r.review_id DESC;
			""")
	@ResultMap("showReviewList")
	List<Review> showListByProductId(Review review, Integer startIndex);

	@Insert("""
		    INSERT INTO review
		    (customer_id, product_id, content, rating)
		    VALUES 
		    (#{customerId}, #{productId}, #{content}, #{rating})
		    """)
		@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "reviewId", before = false, resultType = int.class)
		int addReview(Review review);


	@Insert("""
			INSERT INTO reviewfile
			(review_id, file_name)
			VALUES
			(#{reviewId}, #{fileName})
			""")
	void insertFileName(Integer reviewId, String fileName);

	@Select("""
			SELECT * FROM reviewfile
			WHERE review_id = #{reviewId}
			""")
	void getFileByReviewId(Integer reviewId);

	@Select("""
			SELECT file_name
			FROM reviewfile
			WHERE 
			review_id = #{reviewId}
			""")
	List<String> selectFilesByReviewId(Integer reviewId);

	@Delete("""
			DELETE FROM reviewfile
			WHERE review_id = #{reviewId}
			""")
	void deleteFileByReviewId(Integer reviewId);

	@Delete("""
			DELETE FROM review
			WHERE review_id = #{reviewId}
			""")
	Integer deleteReviewByReviewId(Integer reviewId);

	@Select("""
			SELECT * 
			FROM review r LEFT JOIN reviewfile rf 
			ON r.review_id = rf.review_id 
			WHERE r.review_id = #{reviewId} 
			""")
	@ResultMap("getReviewList")
	Review getReviewByReviewId(Integer reviewId);

	@Delete("""
			DELETE FROM reviewfile
			WHERE
			 	review_id = #{reviewId} AND
				file_name = #{removeFileName}
			""")
	void deleteFileByFileName(Integer reviewId, String removeFileName);

	@Update("""
			UPDATE review
			SET 
			content = #{content},
			rating = #{rating}
			WHERE
			review_id = #{reviewId}
			""")
	Integer updateReviewByReviewId(Review review);

}
