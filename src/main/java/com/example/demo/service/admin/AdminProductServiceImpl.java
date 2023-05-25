package com.example.demo.service.admin;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.admin.*;

import lombok.*;
import software.amazon.awssdk.core.sync.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	private final S3Client s3;

	private final ProductMapper productMapper;

	// 상품목록
	@Override
	public List<ProductView> getViewList() {
		return productMapper.getAllView();
	}

	// 상품목록
	@Override
	public Map<String, Object> getViewList(Integer page, String type, String search, Integer stockQuantity, String pub) {
		// 0~10 10~20 20~30
		Integer pageSize = 10; // 10 20 30 인데 mariadb 10씩
		Integer startIndex = (page - 1) * pageSize; // 0 10 20

		// 페이지 네이션에 필요한 정보
		// 전체 레코드 수
		Integer count = productMapper.countAll(type, search, stockQuantity, pub);

		// 마지막 페이지 번호
		// 총 글개수 -1 / pageSize + 1
		Integer lastPage = (count - 1) / pageSize + 1;

		// 페이지네이션 왼쪽번호 1 11 21 31
		Integer leftPageNumber = (page - 1) / pageSize * pageSize + 1;
		leftPageNumber = Math.max(leftPageNumber, 1);

		// 페이지네이션 오른쪽 번호
		Integer rightPageNumber = leftPageNumber + 9;
		rightPageNumber = Math.min(rightPageNumber, lastPage);

		// 현재 페이지
		Integer currentPageNumber = page;

		// 이전페이지
		Integer prevPageNumber = leftPageNumber - 10;

		// 다음 페이지
		Integer nextPageNumber = leftPageNumber + 10;

		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("lastPageNumber", lastPage);
		pageInfo.put("leftPageNumber", leftPageNumber);
		pageInfo.put("rightPageNumber", rightPageNumber);
		pageInfo.put("currentPageNumber", currentPageNumber);
		pageInfo.put("prevPageNumber", prevPageNumber);
		pageInfo.put("nextPageNumber", nextPageNumber);

		List<ProductView> productList = productMapper.selectAllPaging(page, startIndex, pageSize, type, search, stockQuantity, pub);
		return Map.of("pageInfo", pageInfo, "productList", productList);
	}

	// 상품상세페이지
	@Override
	public ProductView getOneView(Integer productId) {
		return productMapper.getViewById(productId);
	}

	// 상품 추가
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean add(Product product, MultipartFile[] files) throws IOException {
		// 상품 등록
		Integer cnt = productMapper.create(product);

		// 파일등록
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				String objectKey = "meatshop/product/" + product.getProductId() + "/" + file.getOriginalFilename();

				// s3에 파일 업로드
				PutObjectRequest por = PutObjectRequest.builder()
						.bucket(bucketName).acl(ObjectCannedACL.PUBLIC_READ).key(objectKey)
						.build();
				RequestBody rb = RequestBody.fromInputStream(file.getInputStream(),
						file.getSize());

				s3.putObject(por, rb);

				// db에 관련정보저장 (insert)
				productMapper.insertFileName(product.getProductId(), file.getOriginalFilename());
			}
		}
		return cnt == 1;
	}

	// 상품 수정
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean modify(Product product, List<String> removeFileNames, MultipartFile[] files) throws IOException {
		if (removeFileNames != null && !removeFileNames.isEmpty()) {
			for (String fileName : removeFileNames) {
				// 파일 삭제
				String objectKey = "meatshop/product/" + product.getProductId() + "/" + fileName;
				DeleteObjectRequest dor = DeleteObjectRequest.builder()
						.bucket(bucketName)
						.key(objectKey)
						.build();

				s3.deleteObject(dor);

				// FileName 테이블의 데이터 삭제
				productMapper.deleteFileNameByBoardIdAndFileName(product.getProductId(), fileName);
			}
		}

		// 상품 정보 수정
		int cnt = productMapper.update(product);

		// 파일등록
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				String objectKey = "meatshop/product/" + product.getProductId() + "/" + file.getOriginalFilename();

				// s3에 파일 업로드
				PutObjectRequest por = PutObjectRequest.builder()
						.bucket(bucketName).acl(ObjectCannedACL.PUBLIC_READ).key(objectKey)
						.build();
				RequestBody rb = RequestBody.fromInputStream(file.getInputStream(),
						file.getSize());

				s3.putObject(por, rb);

				// db에 관련정보저장 (insert)
				productMapper.insertFileName(product.getProductId(), file.getOriginalFilename());
			}
		}
		return cnt == 1;
	}

	// 상품 삭제 처리
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean remove(Integer productId) {
		// 상품에 설정된 이미지 파일 삭제
		// 파일명 조회
		List<String> fileNames = productMapper.selectFileNamesByProductId(productId);

		// FileName 테이블의 데이터 지우기
		productMapper.deleteFileNameByProductId(productId);

		// s3파일 지우기
		for (String fileName : fileNames) {

			String objectKey = "meatshop/product/" + productId + "/" + fileName;
			DeleteObjectRequest dor = DeleteObjectRequest.builder().bucket(bucketName).key(objectKey).build();

			s3.deleteObject(dor);
		}

		// db에서 상품삭제
		Integer cnt = productMapper.deleteById(productId);
		return cnt == 1;
	}

	@Override
	public boolean pubProductAll(PubRequest pub) {
		
		//공개할 목록들을 리스트로
		List<String> openIds = pub.getOpenIds();
		
		//전체 아이디들을 배열로
		List<String> ids = pub.getIds();
		
		Integer cnt = productMapper.changeProductPub(openIds, ids);
		return cnt != 0;
		
	}

}
