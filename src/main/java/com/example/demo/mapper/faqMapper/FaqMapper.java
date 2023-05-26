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

	@Delete("""
			DELETE FROM faq
			WHERE id = #{id}
			""")
	int deleteById(Integer id);
	
	@Select("""
			SELECT *
			FROM faq
			WHERE id = #{id}
			""")
	@ResultMap("faqResultMap")
	Faq selectById(Integer id);

	@Update("""
			UPDATE faq
			SET
				title = #{title},
				content = #{content},
				category = #{category}
			WHERE
				id = #{id}
			""")
	int update(Faq faq);

	@Insert("""
			INSERT INTO faq (title, content, category)
			VALUES (#{title}, #{content}, #{category})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Faq faq);

}
