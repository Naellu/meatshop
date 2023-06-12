<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- 
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
<body> --%>

<sec:authentication property="name" var="userid" />



<button class="btn btn-primary" id="addInquiry"
	data-product-id="${productInquiry.productId}"
	data-customer-id="${userid }">문의하기</button>
<hr />
<div class="accordion " id="accordionExample">
	<table class="table">

		<tbody>
			<c:forEach items="${productInquiryList}" var="inquiry">
				<tr>
					<td>${inquiry.customerName}</td>
					<td>${inquiry.customerId}</td>
					<td>${inquiry.inquiryTitle}
						<button onclick="listAnswer('${inquiry.inquiryId}')"
							style="background-color: #ffffff;" class="accordion-button"
							type="button" data-bs-toggle="collapse"
							data-bs-target="#collapse${inquiry.inquiryId}"
							aria-expanded="false"
							aria-controls="collapse${inquiry.inquiryId}">문의내용 보기</button>
					</td>
					<td>${inquiry.createdAt}</td>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="name" var="userId" />
						<c:if test="${userId eq inquiry.customerId }">
							<td>
								<button class="btn btn-primary" name="modifyInquiry"
									data-inquiry-id="${inquiry.inquiryId}"
									data-customer-id="${userid }">수정</button>
							</td>
							<td>
								<button class="btn btn-danger" name="removeInquiry"
									data-inquiry-id="${inquiry.inquiryId}"
									data-product-id="${inquiry.productId}"
									data-customer-id="${userid }" data-bs-toggle="modal"
									data-bs-target="#deleteInquiryConfirmModal"
									data-product-id="${product.productId}"
									data-customer-id="${userid }">삭제</button>

							</td>

						</c:if>
					</sec:authorize>
				</tr>
				<tr>
					<td colspan="6">
						<div id="collapse${inquiry.inquiryId}"
							class="accordion-collapse collapse"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div style="white-space: pre-wrap;">
									<textarea class="form-control" rows="5" readonly="readonly">${inquiry.inquiryText}</textarea>
								</div>
								<div class="mb-3" id="answerContainer${inquiry.inquiryId }">
									<!-- 답변이 표시될 구역 -->


								</div>
								<!-- 관리자만 보이게 할 예정 -->
								<sec:authorize access="isAuthenticated()">
									<sec:authentication property="name" var="userId" />
									<c:if test="${userId eq 'admin0' }">
										<div class="mb-3">

											<div class="input-group" id="answerOfInquiry">
												<div class="form-floating">
													<textarea style="height: 97px" class="form-control"
														id="answerTextArea${inquiry.inquiryId }"></textarea>
												</div>
												<button name="sendAnswerButton"
													class="btn btn-outline-primary"
													id="sendAnswerBtn${inquiry.inquiryId }">답변하기</button>
											</div>
										</div>
									</c:if>
								</sec:authorize>

							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">


			<!-- 맨앞 -->
			<c:if test="${pageInfo.currentPageNumber > 1 }">
				<my:productInquiry pageNumber="1"
					productId="${inquiryInfo.productId }"
					customerId="${inquiryInfo.customerId }">
					<i class="fa-solid fa-angles-left"></i>
				</my:productInquiry>
			</c:if>

			<!-- 이전 -->
			<c:if test="${pageInfo.currentPageNumber > 1 }">
				<my:productInquiry pageNumber="${pageInfo.currentPageNumber -1}"
					productId="${inquiryInfo.productId }"
					customerId="${inquiryInfo.customerId }">
					<i class="fa-solid fa-angle-left"></i>
				</my:productInquiry>
			</c:if>


			<c:forEach begin="${pageInfo.begin }" end="${pageInfo.end }"
				var="pageNumber">
				<!-- li.page-item>a.page-link -->
				<my:productInquiry pageNumber="${pageNumber }"
					productId="${inquiryInfo.productId }"
					customerId="${inquiryInfo.customerId }">
				${pageNumber }
			</my:productInquiry>

			</c:forEach>

			<!-- 다음 버튼 -->
			<c:if test="${pageInfo.nextPageNumber le pageInfo.lastPageNumber }">
				<my:productInquiry pageNumber="${pageInfo.currentPageNumber + 1 }"
					productId="${inquiryInfo.productId }"
					customerId="${inquiryInfo.customerId }">
					<i class="fa-solid fa-angle-right"></i>
				</my:productInquiry>
			</c:if>


			<!-- 맨뒤로 -->
			<c:if
				test="${ pageInfo.currentPageNumber < pageInfo.lastPageNumber }">
				<my:productInquiry pageNumber="${pageInfo.lastPageNumber }"
					productId="${inquiryInfo.productId }"
					customerId="${inquiryInfo.customerId }">
					<i class="fa-solid fa-angles-right"></i>
				</my:productInquiry>
			</c:if>
		</ul>
	</nav>
</div>



<%-- <!-- 문의 삭제 모달 -->
<div class="d-none">
	<form action="/product/inquiry/delete" method="post"
		id="inquiryRemoveForm">
		<input type="text" name="productId"
			value="${productInquiry.productId}" /> <input type="text"
			id="removeInquiry" name="inquiryId" value="" />
	</form>
</div> --%>
<div class="modal fade" id="deleteInquiryConfirmModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
				<button type="button" class="btn-outline-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">삭제하시겠습니까?</div>
			<div class="modal-footer">
				<button type="button" id="removeInquiryBtn" data-inquiry-id=""
					data-product-id="" data-customer-id="" data-bs-toggle="modal"
					class="btn btn-outline-danger" form="inquiryRemoveForm"
					data-bs-dismiss="modal">삭제</button>
				<button type="button" class="btn btn-outline-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 문의 답변 삭제 모달 -->
<div class="modal fade" id="deleteAnswerConfirmModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5">댓글 삭제 확인</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">삭제 하시겠습니까?</div>
			<div class="modal-footer">
				<button type="button" id="deleteAnswerModalButton"
					data-bs-dismiss="modal" data-inquiry-id="" type="button"
					class="btn btn-danger">삭제</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>






<script src="/js/product/inquiry/list.js"></script>
<%-- 
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html> --%>