package com.example.demo.service.customer;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.customer.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class CustomerProductService {

	private final CustomerProductMapper productMapper;

	public List<ProductView> getViewList() {
		return productMapper.getAllView();
	}

	public ProductView getOneView(Integer id) {
		return 	productMapper.getViewById(id);
	}



}
