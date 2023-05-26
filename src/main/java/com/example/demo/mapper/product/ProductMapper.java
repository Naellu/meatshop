package com.example.demo.mapper.product;

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
	
	//페이징 처리
	public List<ProductView> selectAllPaging(Integer startIndex, Integer pageSize, String type, String search, Integer stockQuantity, String pub);

	//전체 게시글 개수
	public Integer countAll(String type, String search, Integer stockQuantity, String pub);

	//공개 비공개처리
	public Integer changeProductPub(List<String> openIds, List<String> ids);

	//고객 페이지
	// 고객 상품 목록
	public List<ProductView> getCustomerView();

	//고객 상품 목록
	public List<ProductView> selectCustomerAllPaging(Integer pageSize, Integer startIndex, Integer categoryId, String type, String search);
	
	//고객 상품 상세페이지
	public ProductView getCustomerViewById(Integer id);

	//고객 상품 개수 pub = 1만
	public Integer countCustomerAll(Integer categoryId, String type, String search);
}
