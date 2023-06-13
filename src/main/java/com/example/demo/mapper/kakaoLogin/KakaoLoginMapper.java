package com.example.demo.mapper.kakaoLogin;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.kakaoLogin.*;

@Mapper
public interface KakaoLoginMapper {

	@Insert("""
			Insert Into kakao (nickname, emial)
			VALUES (#{nickname}, #{email})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void kakaoinsert(HashMap<String, Object> userInfo);
	
	@Select("""
			SELECT * FROM kakao 
			WHERE nickname = #{nickname}
			AND email = #{email}
			""")
	Kakao findKakao(HashMap<String, Object> userInfo);
}
