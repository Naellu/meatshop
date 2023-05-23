package com.example.demo.mapper.admin;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface ProductMapper {

	// 상품 목록
	public List<ProductView> getAllView();

	// 상품 상세 페이지
	public ProductView getViewById(Integer productId);

	// 상품삭제
	public Integer deleteById(Integer productId);

	// 상품 추가
	public Integer create(Product product);

	// 파일관련
	// 파일등록
	public void insertFileName(Integer productId, String originalFilename);

	// 상품아이디로 파일 검색
	@Select("""
			SELECT file_name FROM productfilename WHERE product_id = #{productId}
			""")
	public List<String> selectFileNamesByProductId(Integer productId);

	// 상품아이디로 파일 삭제
	public void deleteFileNameByProductId(Integer productId);

	//파일 이름과 상품아이디로 삭제
	public void deleteFileNameByBoardIdAndFileName(Integer productId, String fileName);

	public int update(Product product);
}
