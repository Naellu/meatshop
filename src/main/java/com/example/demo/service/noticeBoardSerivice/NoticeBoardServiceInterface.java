package com.example.demo.service.noticeBoardSerivice;

import java.util.*;

import org.springframework.web.multipart.*;

import com.example.demo.domain.NoticeBoardDomain.*;

public interface NoticeBoardServiceInterface {

	public List<NoticeBoard> getList();
	public NoticeBoard getNoticeBoard(Integer id);
	public boolean modify(NoticeBoard nboard, MultipartFile[] addFiles, List<String> removeFileNames) throws Exception;
	public boolean remove(Integer id);
	public boolean addNoticeBoard(NoticeBoard nboard, MultipartFile[] files) throws Exception;
}
