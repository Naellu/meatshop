<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>

<my:alert></my:alert>

<h1>${param.productId}번상품리뷰</h1>
<hr />
<form action="/product/review/add" method="post"
	enctype="multipart/form-data">
	<input class="form-control" type="hidden" name="productId" value="${review.productId}" />
	<input class="form-control" type="hidden" name="customerId" value="${review.customerId}" />
	<div class="mb-3 form-floating">
		별점 
		<select name="rating" id="rating">
			<option value="1">★</option>
			<option value="2">★★</option>
			<option value="3">★★★</option>
			<option value="4">★★★★</option>
			<option value="5">★★★★★</option>
		</select> <br />
		리뷰 내용 <br />
		<textarea class="form-control" name="content" rows="10" ></textarea>
		<br />
		사진첨부 
		<input class="form-control form-control-sm" id="formFileSm" type="file" name="files" multiple accept="image/*" />
		<div class="form-text"> 파일당 1MB, 총합 10MB를 초과할 수 없습니다.</div>
	</div>
	<input class="btn btn-primary" type="submit" value="등록" id="addReview" />
</form>