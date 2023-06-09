package com.example.demo.mapper.product.review;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ReviewResponseMapper {

	@Insert("""
			INSERT INTO reviewresponse (review_id, response)
			VALUES (#{reviewId}, #{response})
			""")
	int addResponse(ReviewResponse reviewResponse);

	@Delete("""
			DELETE FROM reviewresponse
			WHERE response_id = #{responseId}
			""")
	int removeResponse(Integer responseId);


	@Select("""
			SELECT *
			FROM reviewresponse
			WHERE response_id = #{responseId}
			""")
	@ResultMap("getResponse")
	ReviewResponse getResponseByResponseId(Integer responseId);

	@Update("""
			UPDATE reviewresponse
			SET response = #{response}
			WHERE response_id = #{responseId}
			""")
	int modifyResponse(ReviewResponse reviewResponse);

}
