package com.example.demo.service.noticeBoardSerivice;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.*;
import com.example.demo.domain.NoticeBoardDomain.*;
import com.example.demo.mapper.noticeBoardMapper.*;

import software.amazon.awssdk.core.sync.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;


@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeBoardService implements NoticeBoardServiceInterface {


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


	public boolean modify(NoticeBoard nboard, MultipartFile[] addFiles, List<String> removeFileNames) throws Exception {
		
		// FileName 테이블 삭제
				if (removeFileNames != null && !removeFileNames.isEmpty()) {
					for (String fileName : removeFileNames) {
						// s3에서 파일(객체) 삭제
						String objectKey = "meatshop/noticeboard/" + nboard.getId() + "/" + fileName;
						DeleteObjectRequest dor = DeleteObjectRequest.builder()
								.bucket(bucketName)
								.key(objectKey)
								.build();
						s3.deleteObject(dor);

						// 테이블에서 삭제
						mapper.deleteFileNameByBoardIdAndFileName(nboard.getId(), fileName);
					}
				}

				// 새 파일 추가
				for (MultipartFile newFile : addFiles) {
					if (newFile.getSize() > 0) {
						// 테이블에 파일명 추가
						mapper.insertFileName(nboard.getId(), newFile.getOriginalFilename());

						// s3에 파일(객체) 업로드
						String objectKey = "meatshop/noticeboard/" + nboard.getId() + "/" + newFile.getOriginalFilename();
						PutObjectRequest por = PutObjectRequest.builder()
								.acl(ObjectCannedACL.PUBLIC_READ)
								.bucket(bucketName)
								.key(objectKey)
								.build();
						RequestBody rb = RequestBody.fromInputStream(newFile.getInputStream(), newFile.getSize());
						s3.putObject(por, rb);
					}
				}
		int cnt = mapper.update(nboard);

		return cnt == 1;
	}

	public boolean remove(Integer id) {

		List<String> fileNames = mapper.selectFileNamesByBoardId(id);
		mapper.deleteFileNameByBoardId(id);
		
		// s3 bucket의 파일(객체) 지우기
				for (String fileName : fileNames) {
					String objectKey = "meatshop/noticeboard/" + id + "/" + fileName;
					DeleteObjectRequest dor = DeleteObjectRequest.builder()
							.bucket(bucketName)
							.key(objectKey)
							.build();
					s3.deleteObject(dor);
				}
		int cnt = mapper.deleteById(id);

		return cnt == 1;
	}

	public boolean addNoticeBoard(NoticeBoard nboard, MultipartFile[] files) throws Exception {
		int cnt = mapper.insert(nboard);
    
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				String objectKey = "meatshop/noticeboard/" + nboard.getId() + "/" + file.getOriginalFilename();

				PutObjectRequest por = PutObjectRequest.builder().bucket(bucketName).key(objectKey)
						.acl(ObjectCannedACL.PUBLIC_READ).build();
				RequestBody rb = RequestBody.fromInputStream(file.getInputStream(), file.getSize());

				s3.putObject(por, rb);

				mapper.insertNoticeboardfile(nboard.getId(), file.getOriginalFilename());
			}
		}
		return cnt == 1;

	}
	
	public Map<String, Object> getList(Integer page, String search, String type) {
		Integer rowPerPage = 10;
		
		Integer startIndex = (page -1) * rowPerPage;
		
		Integer numOfRecords = mapper.countAll(search, type);
		
		Integer lastPageNumber = (numOfRecords -1) / rowPerPage + 1;
		
		Integer leftPageNum = page - 5;
		
		leftPageNum = Math.max(leftPageNum, 1);
		
		Integer rightPageNum = leftPageNum + 9;
		
		rightPageNum = Math.min(rightPageNum, lastPageNumber);
		
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("rightPageNum", rightPageNum);
		pageInfo.put("leftPageNum", leftPageNum);
		pageInfo.put("currentPageNum", page);
		pageInfo.put("lastPageNum", lastPageNumber);
		
		List<NoticeBoard> list = mapper.selectAllPaging(startIndex, rowPerPage, search, type);

		return Map.of("pageInfo", pageInfo, "noticeBoardList", list);
	}

}
