<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<my:navBar></my:navBar>

	<my:alert />


	<h1>${param.productId}번상품문의목록</h1>
	<hr />

	<div class="accordion " id="accordionExample">
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
				<c:forEach items="${productInquiryList}" var="inquiry">
					<tr>
						<td>${inquiry.customerName}</td>
						<td>${inquiry.nickName}</td>
						<td>
							<button style="background-color: #ffffff;" class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${inquiry.inquiryId}" aria-expanded="false" aria-controls="collapse${inquiry.inquiryId}">${inquiry.inquiryTitle}</button>
						</td>
						<td>${inquiry.createdAt}</td>
						<td>
							<button class="btn btn-primary" onclick="location.href='/productInquiry/modify/${inquiry.inquiryId}'">수정</button >
						</td>
						<td>
							<button name="removeButton" id="${inquiry.inquiryId}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<div id="collapse${inquiry.inquiryId}" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
								<div class="accordion-body">
									<div style="white-space: pre-wrap;">${inquiry.inquiryText}</div>
									<div>
										<button class="btn btn-primary" id="answer${inqpuriy.inquiryId }" name="answerButton"></button>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>





	<div class="d-none">
		<form action="/productInquiry/delete" method="post" id="removeForm">
			<input type="text" name="productId" value="${param.productId}" />
			<input type="text" name=inquiryId value="" />
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