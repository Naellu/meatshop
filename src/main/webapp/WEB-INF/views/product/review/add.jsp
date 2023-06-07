<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>

<my:alert></my:alert>

<h1>${param.productId}번상품리뷰</h1>
<hr />
<form action="/product/review/add" method="post"
	enctype="multipart/form-data">
	<input type="hidden" id="productId" value="${review.productId}" />
	<input type="hidden" id="customerId" value="${review.customerId}" />
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
		<textarea class="form-control" id="reviewContent" rows="10" ></textarea>
		<br />
	</div>
	<input class="btn btn-primary" type="button" value="등록" id="addReview" />
</form>