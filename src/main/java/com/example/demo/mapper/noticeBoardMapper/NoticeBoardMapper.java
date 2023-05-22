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
}
