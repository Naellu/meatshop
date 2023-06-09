package com.example.demo.service.admin;

import java.io.*;
import java.util.*;

import org.springframework.web.multipart.*;

import com.example.demo.domain.*;

public interface ProductService {
	// 상품 목록
	public List<ProductView> getViewList();
	
	// 상품 목록 + 페이징
	public Map<String, Object> getViewList(Integer page, String type, String search, Integer stockQuantity, String pub);
	
	//상품 상세 페이지
	public ProductView getOneView(Integer productId);

	// 상품삭제
	public Boolean remove(Integer productId);

	//상품 추가
	public boolean add(Product product, MultipartFile[] files) throws IOException;

	//상품 수정
	public boolean modify(Product product, List<String> removeFileNames, MultipartFile[] files) throws IOException;

	//상품 공개처리
	public boolean pubProductAll(PubRequest pub);
}
