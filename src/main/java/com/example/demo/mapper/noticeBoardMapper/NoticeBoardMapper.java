package com.example.demo.mapper.noticeBoardMapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.NoticeBoardDomain.*;

@Mapper
public interface NoticeBoardMapper {

	@Select("""
			SELECT
				id,
				title,
				writer,
				inserted
			FROM noticeboard
			ORDER BY id DESC
			""")
	List<NoticeBoard> selectAll();

	@Select("""
			SELECT *
			FROM noticeboard
			WHERE
				id = #{id}
			""")
	NoticeBoard selectById(Integer id);

	@Update("""
			UPDATE noticeboard
			SET
				title = #{title},
				content = #{content}
			WHERE
				id = #{id}
			""")
	int update(NoticeBoard nboard);

	@Delete("""
			DELETE FROM noticeboard
			WHERE id = #{id}
			""")
	int deleteById(Integer id);

	@Insert("""
			INSERT INTO noticeboard (title, content, writer)
			VALUES (#{title}, #{content}, #{writer})
			""")
	int insert(NoticeBoard nboard);

	@Insert("""
			INSERT INTO noticeboardfile (notice_board_id, file_name)
			VALUES (#{notice_board_id}, #{file_name})
			""")
	Integer insertNoticeboardfile(Integer notice_board_id, String file_name);
}
