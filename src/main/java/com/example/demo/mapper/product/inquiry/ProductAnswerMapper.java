package com.example.demo.mapper.product.inquiry;


import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ProductAnswerMapper {

	
	@Insert("""
			INSERT INTO productanswer(inquiry_id, answer)
			VALUES (#{inquiryId}, #{answer})
			""")
	@ResultMap("addAnswer")
	int addAnswer(ProductAnswer productAnswer);

	@Select("""
			SELECT *
			FROM productanswer
			WHERE 
			inquiry_id = #{inquiryId}
			""")
	@ResultMap("getAnswersByInqruiryId")
	ProductAnswer getAnswersByInqruiryId(Integer inquiryId);

	@Update("""
			UPDATE productanswer
			SET answer = #{answer}
			WHERE inquiry_id = #{inquiryId}
			""")
	int modifyAnswerByInquiryId(ProductAnswer productAnswer);
	
	
	@Delete("""
			DELETE
			FROM productanswer
			WHERE inquiry_id = #{inquiryid}
			""")
	int removeAnswerByInquiryId(Integer inquiryid);

	
	

}
