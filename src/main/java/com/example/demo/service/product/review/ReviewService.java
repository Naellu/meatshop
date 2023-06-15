package com.example.demo.service.product.review;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.product.review.*;

import software.amazon.awssdk.core.sync.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReviewService {

	@Autowired
	private S3Client s3;

	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private ReviewLikeMapper likeMapper;
	
	@Autowired
	private ReviewResponseMapper responseMaper;

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	public Map<String, Object> showReviewListByProductId(
			Review review,
			Integer page,
			Authentication authentication) {

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

		ReviewLike reviewLike = new ReviewLike();

		for (Review reviewElement : reviewList) {
			reviewElement.setLikeCount(0); // 초기값 설정
			reviewElement.setLiked(false); // 초기값 설정

			reviewLike.setReviewId(reviewElement.getReviewId());

			if (authentication != null) {
				reviewLike.setCustomerId(authentication.getName());
				reviewElement.setLiked(likeMapper.likedByCustomerId(reviewLike) == 1);
			}

			reviewElement.setLikeCount(likeMapper.likeCount(reviewLike));
		}

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
		
		responseMaper.removeResponsesByReviewId(reviewId);
		
		likeMapper.deleteLikesByReviewId(reviewId);

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

		if (removeFileNames != null && !removeFileNames.isEmpty()) {
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
			if (newFile.getSize() > 0) {
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

	public Map<String, Object> reviewLike(ReviewLike reviewLike, Authentication authentication) {
		Map<String, Object> result = new HashMap<>();

		result.put("like", false);
		reviewLike.setCustomerId(authentication.getName());

		Integer deleteCnt = likeMapper.deleteLike(reviewLike);

		if (deleteCnt != 1) {
			result.put("like", true);
			likeMapper.insertLike(reviewLike);
		}

		Integer likeCount = likeMapper.likeCount(reviewLike);

		result.put("count", likeCount);

		return result;
	}

}
