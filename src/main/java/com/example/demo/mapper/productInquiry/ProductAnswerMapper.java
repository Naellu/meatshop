package com.example.demo.mapper.productInquiry;

import java.util.*;

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
			SELECT answer
			FROM productanswer
			WHERE 
			inquiry_id = #{inquiryId}
			""")
	List<Object> getAnswersByInqruiryId(Integer inquiryId);
	
	

}
