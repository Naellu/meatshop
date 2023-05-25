package com.example.demo.service.customer;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.customer.*;

import lombok.*;

@Service("customerProductServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final CustomerProductMapper productMapper;

	@Override
	public List<ProductView> getViewList() {
		return productMapper.getAllView();
	}

	@Override
	public ProductView getOneView(Integer id) {
		return productMapper.getViewById(id);
	}

}
