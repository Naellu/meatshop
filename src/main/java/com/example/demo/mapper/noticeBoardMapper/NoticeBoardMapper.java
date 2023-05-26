package com.example.demo.mapper.noticeBoardMapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;
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
			SELECT 
				nb.id,
				nb.title,
				nb.content,
				nb.inserted,
				nbf.file_name
			FROM noticeboard nb LEFT JOIN noticeboardfile nbf 
			ON nb.id = nbf.notice_board_id
			WHERE
				nb.id = #{id}
			""")
	@ResultMap("noticeboardResultMap")
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
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(NoticeBoard nboard);

	@Insert("""
			INSERT INTO noticeboardfile (notice_board_id, file_name)
			VALUES (#{notice_board_id}, #{file_name})
			""")
	Integer insertNoticeboardfile(Integer notice_board_id, String file_name);

	@Select("""
			SELECT file_name FROM noticeboardfile
			WHERE notice_board_id = #{notice_board_id}
			""")
	List<String> selectFileNamesByBoardId(Integer notice_board_id);

	@Delete("""
			DELETE FROM noticeboardfile
			WHERE notice_board_id = #{notice_board_id}
			""")
	void deleteFileNameByBoardId(Integer id);

	@Delete("""
			DELETE FROM noticeboardfile
			WHERE notice_board_id = #{notice_board_id}
			AND file_name = #{file_name}
			""")
	void deleteFileNameByBoardIdAndFileName(Integer notice_board_id, String file_name);

	@Insert("""
			INSERT INTO noticeboardfile (notice_board_id, file_name)
			VALUES (#{notice_board_id}, #{file_name})
			""")
	void insertFileName(Integer notice_board_id, String file_name);

	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT
				nb.id,
				nb.title,
				nb.writer,
				nb.inserted,
				COUNT(f.id) fileCount
			FROM noticeboard nb LEFT JOIN noticeboardfile f ON nb.id = f.notice_board_id
			
			<where>
				<if test="(type eq 'all') or (type eq 'title')">
				   title  LIKE #{pattern}
				</if>
				<if test="(type eq 'all') or (type eq 'content')">
				OR content   LIKE #{pattern}
				</if>
				<if test="(type eq 'all') or (type eq 'writer')">
				OR writer LIKE #{pattern}
				</if>
			</where>
			
			GROUP BY nb.id
			ORDER BY nb.id DESC
			LIMIT #{startIndex}, #{rowPerPage}
			</script>
			""")
	List<NoticeBoard> selectAllPaging(Integer startIndex, Integer rowPerPage, String search, String type);

	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT COUNT(*) 
			FROM noticeboard
			
			<where>
				<if test="(type eq 'all') or (type eq 'title')">
				   title  LIKE #{pattern}
				</if>
				<if test="(type eq 'all') or (type eq 'content')">
				OR content   LIKE #{pattern}
				</if>
				<if test="(type eq 'all') or (type eq 'writer')">
				OR writer LIKE #{pattern}
				</if>
			</where>
			
			</script>
			""")
	Integer countAll(String search, String type);
}
