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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<my:alert></my:alert>

	<h1>${param.productId}번상품문의</h1>
	<hr />
	<form action="/product/inquiry/add" method="post"
		enctype="multipart/form-data">
		<input type="hidden" id="productId" name="productId"
			value="${productInquiry.productId}" /> <input type="hidden"
			id="customerId" name="customerId"
			value="${productInquiry.customerId}" />
		<div class="mb-3">
			제목 <input class="form-control" type="text" id="inquiryTitle"
				name="inquiryTitle" /><br /> 문의 내용
			<textarea class="form-control" id="bodyTextarea" rows="10"
				name="inquiryText"></textarea>
		</div>
		<i class="fa-solid fa-lock">비밀문의</i>
		<input type="checkbox" id="disclosure" name="disclosure" checked="checked"><br /> <br /> 
		<input class="btn btn-primary" type="button" value="등록"
			id="addInquiry" />
		<button class="btn btn-secondary" type="button"
			onclick="window.location.reload()">취소</button>
	</form>





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