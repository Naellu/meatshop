package com.example.demo.service.customer;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.*;

import lombok.*;

@Service("customerProductServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductMapper productMapper;

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

		List<ProductView> productList = productMapper.selectCustomerAllPaging(pageSize, startIndex, categoryId, type, search);
		return Map.of("pageInfo", pageInfo, "productList", productList);
	}

	@Override
	public ProductView getOneView(Integer id) {
		return productMapper.getCustomerViewById(id);
	}

}
