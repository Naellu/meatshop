package com.example.demo.service.noticeBoardSerivice;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.NoticeBoardDomain.*;
import com.example.demo.mapper.noticeBoardMapper.*;


@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBoardService {

	@Autowired
	private NoticeBoardMapper mapper;
	
	public List<NoticeBoard> getList() {
		List<NoticeBoard> list = mapper.selectAll();
		
		return list;
	}

	public NoticeBoard getNoticeBoard(Integer id) {
		NoticeBoard nb = mapper.selectById(id);
		return nb;
	}

}
