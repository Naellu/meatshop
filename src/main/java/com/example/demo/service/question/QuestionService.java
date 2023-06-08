package com.example.demo.service.question;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.question.*;
import com.example.demo.mapper.question.*;

import software.amazon.awssdk.core.sync.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;

@Service
public class QuestionService {

	@Autowired
	private S3Client s3;

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	@Autowired
	private QuestionMapper mapper;

	public List<Question> getList() {
		List<Question> list = mapper.selectAll();
		for (Question question : list) {
			boolean answered = mapper.isAnswered(question.getId());
			question.setAnswered(answered);
		}
		return list;
	}

	public Question getQuestion(Integer id) {
		Question q = mapper.selectById(id);
		return q;
	}

	public boolean addQuestion(Question question, MultipartFile[] files) throws Exception {
		int cnt = mapper.insert(question);

		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				String objectKey = "meatshop/question/" + question.getId() + "/" + file.getOriginalFilename();

				PutObjectRequest por = PutObjectRequest.builder().bucket(bucketName).key(objectKey)
						.acl(ObjectCannedACL.PUBLIC_READ).build();
				RequestBody rb = RequestBody.fromInputStream(file.getInputStream(), file.getSize());

				s3.putObject(por, rb);

				mapper.insertQuestionfile(question.getId(), file.getOriginalFilename());
			}
		}
		return cnt == 1;
	}

	public Map<String, Object> getList(Integer page, String search) {

		Integer rowPerPage = 10;

		Integer startIndex = (page - 1) * rowPerPage;

		Integer numOfRecords = mapper.countAll(search);

		Integer lastPageNumber = (numOfRecords - 1) / rowPerPage + 1;

		Integer leftPageNum = page - 5;

		leftPageNum = Math.max(leftPageNum, 1);

		Integer rightPageNum = leftPageNum + 9;

		rightPageNum = Math.min(rightPageNum, lastPageNumber);
		
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("rightPageNum", rightPageNum);
		pageInfo.put("leftPageNum", leftPageNum);
		pageInfo.put("currentPageNum", page);
		pageInfo.put("lastPageNum", lastPageNumber);

		List<Question> list = mapper.selectAllPaging(startIndex, rowPerPage, search);

		return Map.of("pageInfo", pageInfo, "questionList", list);
	}



}
