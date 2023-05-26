package com.example.demo.service.faqService;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.faqDomain.*;
import com.example.demo.mapper.faqMapper.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class FaqService {

	@Autowired
	private FaqMapper mapper;
	
	public List<Faq> getList( ) {
		List<Faq> list = mapper.selectAll();
		
		return list;
	}

	public boolean remove(Integer id) {
		int cnt = mapper.deleteById(id);
		
		return cnt == 1;
	}

	

}
