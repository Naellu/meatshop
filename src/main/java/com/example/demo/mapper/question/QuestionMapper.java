package com.example.demo.mapper.question;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.question.*;

@Mapper
public interface QuestionMapper {

	@Select("""
			SELECT
			id,
			customer_id,
			content,
			inserted,
			answered,
			title
			FROM question
			ORDER BY id DESC
			""")
	List<Question> selectAll();
	
	@Select("""
			SELECT
			q.id,
			q.title,
			q.content,
			q.inserted,
			q.answered,
			q.customer_id,
			f.file_name
			FROM question q LEFT JOIN qfile f
			ON q.id = f.q_id
			WHERE
			q.id = #{id}
			""")
	@ResultMap("questionResultMap")
	Question selectById(Integer id);
	
	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT COUNT(*)
			FROM question

			<where>
				<if test="(search eq 'answered')">
					answered IS NOT NULL
				</if>
				<if test="(search eq 'unanswered')">
					answered IS NULL
				</if>
			</where>

			</script>
			""")
	Integer countAll(String search);

	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT
				q.id,
				q.title,
				q.inserted,
				q.answered,
				COUNT(f.id) fileCount
			FROM question q LEFT JOIN qfile f ON q.id = f.q_id

			<where>
				<if test="(search eq 'answered')">
					answered IS NOT NULL
				</if>
				<if test="(search eq 'unanswered')">
					answered IS NULL
				</if>
			</where>
			
			GROUP BY q.id
			ORDER BY q.id DESC
			LIMIT #{startIndex}, #{rowPerPage}
			</script>
			""")
	List<Question> selectAllPaging(Integer startIndex, Integer rowPerPage, String search);

	@Insert("""
			INSERT INTO question (title, content)
			VALUES (#{title}, #{content})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Question question);

	@Insert("""
			INSERT INTO qfile (q_id, file_name)
			VALUES (#{q_id}, #{file_name})
			""")
	Integer insertQuestionfile(Integer q_id, String file_name);

	@Select("""
	        SELECT answered
	        FROM question
	        WHERE id = #{id}
	        """)
	boolean isAnswered(Integer id);

	@Select("""
			SELECT * FROM question
			""")
	List<Question> getAllQuestions();

	@Select("""
			SELECT * FROM question WHERE answered IS NOT NULL
			""")
	List<Question> getAnsweredQuestions();

	@Select("""
			SELECT * FROM question WHERE answered IS NULL
			""")
	List<Question> getUnansweredQuestions();



}
