package com.example.demo.mapper.faqMapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.faqDomain.*;

@Mapper
public interface FaqMapper {

	@Select("""
			SELECT *
			FROM faq
			""")
	List<Faq> selectAll();

}
