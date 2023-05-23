<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<my:navBar></my:navBar>

	<my:alert />

	<h1>${param.id}번상품문의목록</h1>
	<hr />

	<table class="table">
		<thead>
			<tr>
				<th scope="col">고객이름</th>
				<th scope="col">고객닉네임</th>
				<th scope="col">제목</th>
				<th scope="col">작성시각</th>
				<th scope="col">수정</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${inquiryList }" var="inquiry" varStatus="loop">
				<tr>
					<td>${inquiry.customer_name }</td>
					<td>${inquiry.nickname }</td>
					<td>${inquiry.inquiry_title }</td>
					<td>${inquiry.created_at }</td>
					<td>
						<a href="/inquiry/modify/id=${inquiry.id }">수정하기</a>
					</td>
					<td>
						<button name="removeButton" id="${inquiry.id }" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">
						${inquiry.id }삭제
						</button>
					</td>

				</tr>
				<tr>
					<td>
						<div style="white-space: pre-wrap;">${inquiry.inquiry_text }</div>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>


	<!-- <div class="d-none"> -->
	<div>
		<form action="/inquiry/delete" method="post" id="removeForm">
			<input type="text" name="productId" value="${param.id}" } />
			<input type="text" name="inquiryId" value="" />
		</form>
	</div>

	<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
					<button type="button" class="btn-outline-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">삭제하시겠습니까?</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-outline-danger" form="removeForm">삭제</button>
					<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/productInquiry/list.js"></script>
	
	
</body>
</html>