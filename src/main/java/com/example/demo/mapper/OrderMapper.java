package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
	
	// 주문 요청 시 주문상세에 저장
	@Insert("""
			
			""")
	int insertOrderItem();
	
	// 회원정보 가져오기
	@Select("""
			SELECT 
			FROM
			WHERE
			""")
	Integer selectByMemberId();
	
	// 상품정보 가져오기
	@Select("""
			SELECT 
			FROM
			WHERE
			""")
	Integer selectByProductId();
	
	
	
}
