package com.example.demo.mapper.answer;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.answer.*;

@Mapper
public interface AnswerMapper {
	
	@Select("""
			SELECT *
			FROM answer
			WHERE question_id = #{question_id}
			ORDER BY id
			""")
	List<Answer> selectAllByQuestionId(Integer questionId);

	@Insert("""
			INSERT INTO answer (question_id, content, admin_id)
			VALUES (#{question_id}, #{content}, #{admin_id})
			""")
	Integer insert(Answer answer);

	@Select("""
			SELECT *
			FROM answer
			WHERE id = #{id}
			""")
	@ResultMap("answerResultMap")
	Answer selectById(Integer id);

	@Update("""
			UPDATE answer
			SET
				content = #{content}
			WHERE
				id = #{id}
			""")
	Integer update(Answer answer);

	@Delete("""
			DELETE FROM answer
			WHERE id = #{id}
			""")
	Integer deleteById(Integer id);
}
