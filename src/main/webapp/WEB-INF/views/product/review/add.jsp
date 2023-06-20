<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<style>
.accordion-button::after {
	display: none;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
</head>
<body>

	<my:navBar></my:navBar>
	<my:alert></my:alert>

	<div class="container">
		<h1>${param.productId}번상품 리뷰</h1>
		<hr />
		<form action="/product/review/add" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="productId" value="${review.productId}" />
			<input type="hidden" name="customerId" value="${review.customerId}" />
			<div class="mb-3">
				<label for="rating" class="form-label">별점</label> <select
					class="form-select" name="rating" id="rating">
					<option value="1">★</option>
					<option value="2">★★</option>
					<option value="3">★★★</option>
					<option value="4">★★★★</option>
					<option value="5">★★★★★</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">리뷰 내용</label>
				<textarea class="form-control" name="content" rows="5"></textarea>
			</div>
			<div class="mb-3">
				<label for="files" class="form-label">사진 첨부</label> <input
					class="form-control" id="files" type="file" name="files" multiple
					accept="image/*" />
				<div class="form-text">파일당 1MB, 총합 10MB를 초과할 수 없습니다.</div>
			</div>
			<input class="btn btn-primary" type="submit" value="등록"
				id="addReview" />
		</form>
	</div>


	<my:footer></my:footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
		integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>