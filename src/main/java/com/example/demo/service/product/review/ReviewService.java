package com.example.demo.service.product.review;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.review.*;

import software.amazon.awssdk.core.sync.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;

@Service
public class ReviewService {

	@Autowired
	private S3Client s3;

	@Autowired
	private ReviewMapper reviewMapper;

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	public Map<String, Object> showReviewListByProductId(Review review, Integer page) {

		Integer startIndex = (page - 1) * 10;

		Integer size = reviewMapper.size(review);

		Integer leftPageNumber = Math.max(1, page - 5);
		Integer rightPageNumber = Math.min(size, page + 5);

		// 이전버튼 페이지 번호 구하기
		Integer prevPageNumber = Math.max(1, page - 1);
		Integer nextPageNumber = Math.min(size, page + 1);

		// 마지막 페이지 구하기
		Integer lastPageNumber = (size - 1) / 10 + 1;

		rightPageNumber = Math.min(rightPageNumber, lastPageNumber);

		Map<String, Object> pageInfo = new HashMap<>();

		pageInfo.put("begin", leftPageNumber);
		pageInfo.put("end", rightPageNumber);
		pageInfo.put("prevPageNumber", prevPageNumber);
		pageInfo.put("nextPageNumber", nextPageNumber);
		pageInfo.put("lastPageNumber", lastPageNumber);
		pageInfo.put("currentPageNumber", page);
		pageInfo.put("num", 10);

		List<Review> reviewList = reviewMapper.showListByProductId(review, startIndex);

		System.out.println(reviewList);

		Map<String, Object> reviewInfo = new HashMap<>();
		reviewInfo.put("productId", review.getProductId());
		reviewInfo.put("customerId", review.getCustomerId());

		Map<String, Object> res = new HashMap<>();
		res.put("reviewList", reviewList);
		res.put("pageInfo", pageInfo);
		res.put("reviewInfo", reviewInfo);
		return res;
	}

	public boolean addReview(Review review, MultipartFile[] files) throws Exception {
		int check = reviewMapper.addReview(review);

		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				String dirKey = "meatshop/review/" + review.getReviewId();
				String fileKey = dirKey + "/" + file.getOriginalFilename();

				PutObjectRequest por = PutObjectRequest.builder()
						.key(fileKey)
						.acl(ObjectCannedACL.PUBLIC_READ)
						.bucket(bucketName)
						.build();

				RequestBody rb = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
				s3.putObject(por, rb);

				reviewMapper.insertFileName(review.getReviewId(), file.getOriginalFilename());
			}
		}

		return check == 1;
	}

	public boolean remove(Integer reviewId) {

		List<String> files = reviewMapper.selectFilesByReviewId(reviewId);

		reviewMapper.deleteFileByReviewId(reviewId);

		for (String file : files) {
			String dirKey = "meatshop/review/" + reviewId;
			String fileKey = dirKey + "/" + file;
			
			DeleteObjectRequest dor = DeleteObjectRequest.builder()
					.bucket(bucketName)
					.key(fileKey)
					.build();
			s3.deleteObject(dor);
		}

		Integer removeCheck = reviewMapper.deleteReviewByReviewId(reviewId);

		return removeCheck == 1;
	}

	
	public Review getReview(Integer reviewId) {
		return reviewMapper.getReviewByReviewId(reviewId);
	}
	

	public boolean modify(Review review, MultipartFile[] addFiles, List<String> removeFileNames) throws Exception {
		String dirKey = "meatshop/review/" + review.getReviewId();
		
		if(removeFileNames != null && !removeFileNames.isEmpty()) {
			for (String removeFileName : removeFileNames) {
				
				String fileKey = dirKey + "/" + removeFileName;
				
				DeleteObjectRequest dor = DeleteObjectRequest.builder()
						.key(fileKey)
						.bucket(bucketName)
						.build();
				s3.deleteObject(dor);
				
				reviewMapper.deleteFileByFileName(review.getReviewId(), removeFileName);
			}
			
		}
		
		for (MultipartFile newFile : addFiles) {
			if(newFile.getSize() > 0) {
				reviewMapper.insertFileName(review.getReviewId(), newFile.getOriginalFilename());
				
				String fileName = newFile.getOriginalFilename();
				String fileKey = dirKey + "/" + fileName;
				
				PutObjectRequest por = PutObjectRequest.builder()
						.key(fileKey)
						.acl(ObjectCannedACL.PUBLIC_READ)
						.bucket(bucketName)
						.build();
				
				RequestBody rb = RequestBody.fromInputStream(newFile.getInputStream(), newFile.getSize());
				
				s3.putObject(por, rb);
				
			}
		}
		
		Integer modifyCheck = reviewMapper.updateReviewByReviewId(review);
		return modifyCheck == 1;
	}

}
