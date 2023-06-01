package com.example.demo.service.customer;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.*;

import lombok.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;

@Service("customerProductServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	private final ProductMapper productMapper;

	private final S3Client s3;

	@Override
	public Map<String, Object> getViewList(Integer page, Integer categoryId, String type, String search) {
		// 0~10 10~20 20~30
		Integer pageSize = 8; // 8 16 24
		Integer startIndex = (page - 1) * pageSize; // 0 8 16

		// 페이지 네이션에 필요한 정보
		// 전체 레코드 수
		Integer count = productMapper.countCustomerAll(categoryId, type, search);

		// 마지막 페이지 번호
		// 총 글개수 -1 / pageSize + 1
		Integer lastPage = (count - 1) / pageSize + 1;

		// 페이지 기준 6페이지면 1~10 좌 5 현재페이지 우 4
		// 페이지네이션 왼쪽번호
		Integer leftPageNumber = page - 5;
		leftPageNumber = Math.max(leftPageNumber, 1);

		// 페이지네이션 오른쪽 번호
		Integer rightPageNumber = leftPageNumber + 9;
		rightPageNumber = Math.min(rightPageNumber, lastPage);

		// 현재 페이지
		Integer currentPageNumber = page;

		// 이전페이지
		Integer prevPageNumber = currentPageNumber - 1;

		// 다음 페이지
		Integer nextPageNumber = currentPageNumber + 1;

		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("lastPageNumber", lastPage);
		pageInfo.put("leftPageNumber", leftPageNumber);
		pageInfo.put("rightPageNumber", rightPageNumber);
		pageInfo.put("currentPageNumber", currentPageNumber);
		pageInfo.put("prevPageNumber", prevPageNumber);
		pageInfo.put("nextPageNumber", nextPageNumber);

		List<ProductView> productList = productMapper.selectCustomerAllPaging(pageSize, startIndex, categoryId, type,
				search);
		return Map.of("pageInfo", pageInfo, "productList", productList);
	}

	@Override
	public ProductView getOneView(Integer id) {
		return productMapper.getCustomerViewById(id);
	}

	// 메인페이지 top 3 가져오기
	@Override
	public Map<String, Object> getTopView() {

		String prefix = "meatshop/main/carousel/"; // 원하는 경로

		// ListObjectsV2Request를 사용하여 S3 버킷의 객체 목록을 가져옴
		ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
				.bucket(bucketName)
				.prefix(prefix)
				.build();

		// 객체 목록 요청 실행
		ListObjectsV2Response listResponse = s3.listObjectsV2(listRequest);

		// 파일 개수 출력
		int fileCount = (int) listResponse.contents().stream()
				.filter(s3Object -> !s3Object.key().endsWith("/"))
				.count();

		// S3 클라이언트 종료
		List<ProductView> productList = productMapper.getTopView();
		return Map.of("fileCount", fileCount, "productList", productList);
	}

}
