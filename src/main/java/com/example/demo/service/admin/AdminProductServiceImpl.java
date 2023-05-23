package com.example.demo.service.admin;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.admin.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

	private final ProductMapper productMapper;

	@Override
	public List<ProductView> getViewList() {
		return productMapper.getAllView();
	}

	@Override
	public ProductView getOneView(Integer productId) {
		return productMapper.getViewById(productId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean remove(Integer productId) {
		Integer cnt = productMapper.deleteById(productId);
		return cnt == 1;
	}

}
