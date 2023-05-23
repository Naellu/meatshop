package com.example.demo.service.noticeBoardSerivice;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.NoticeBoardDomain.*;
import com.example.demo.mapper.noticeBoardMapper.*;

import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;


@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBoardService implements NoticeBoardServiceInterface{


	@Autowired
	private S3Client s3;

	@Value("${aws.s3.bucketName}")
	private String bucketName;
	
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

	public boolean modify(NoticeBoard nboard) {
		int cnt = mapper.update(nboard);
		
		return cnt == 1;
	}

	public boolean remove(Integer id) {
		int cnt = mapper.deleteById(id);
		
		return cnt == 1;
	}

	public boolean addNoticeBoard(NoticeBoard nboard, MultipartFile[] files) {
		int cnt = mapper.insert(nboard);
		
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				String objectKey = "noticeBoard/" + nboard.getId() + "/" + file.getOriginalFilename();
				
				PutObjectRequest por = PutObjectRequest.builder()
						.bucket(bucketName)
						.key(objectKey)
						.acl(ObjectCannedACL.PUBLIC_READ)
						.build();
				RequestBody rb = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
				
				s3.putObject(por, rb);
				
				mapper.insertNoticeboardfile(nboard.getId(), file.getOriginalFilename());
			}
		}
		return cnt == 1;
	}

}
