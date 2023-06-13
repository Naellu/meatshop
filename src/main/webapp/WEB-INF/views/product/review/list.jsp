<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authentication property="name" var="userid" />
<%-- 
<button class="btn btn-primary" id="addReview"
	data-product-id="${review.productId}" data-customer-id="${userid }">리뷰작성하기</button>
 --%>
<br />
<div class="container">
	<c:forEach items="${reviewList}" var="review">
		<div class="row">
			<div class="col-md-10">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">구매자: ${review.customerId}</h5>
						<p class="card-text">구매상품: ${review.productId}</p>
						<p class="card-text">${review.content}</p>
						<p class="card-text">평점: 
						<c:forEach begin="1" end="${review.rating }" >
							<i class="fa-solid fa-star"></i>
						</c:forEach>
						</p>
						<p class="card-text">
							<c:forEach items="${review.fileNames}" var="fileName">
								<img class="img-thumbnail img-fluid"
									src="${bucketUrl}/review/${review.reviewId}/${fileName}" alt="" />
							</c:forEach>
						</p>
						<p class="card-text">
							<small class="text-muted">${review.createdAt }</small>
						</p>
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="name" var="userId" />
							<c:if test="${userId eq review.customerId}">
								<p class="card-text">
									<button class="btn btn-primary" name="modifyReview"
										data-review-id="${review.reviewId}"
										data-customer-id="${userid}">리뷰수정</button>
									<button class="btn btn-danger" name="removeReview"
										data-review-id="${review.reviewId}" data-bs-toggle="modal"
										data-bs-target="#deleteReviewConfirmModal">리뷰삭제</button>
								</p>
							</c:if>
						</sec:authorize>
						<p class="card-text">
							<c:if test="${review.responses[0] != null}">
								<h3>관리자 답변</h3>
								<div class="row">
									<div class="col-md-12">
										<div class="card">
											<br />
											<c:forEach items="${review.responses}" var="response">
												<div class="container">
													<div class="row">
														<div class="col-md-10">
															<div class="mb-3">
																<textarea style="height: 97px" class="form-control">${response.response}</textarea>
															</div>
															<div class="mb-3">
																<p>${response.createdAt}</p>
															</div>
															<div class="mb-3">
																<button class="btn btn-primary" name="modifyResponse"
																	data-response-id="${response.responseId}"
																	data-product-id="${review.productId}">답변수정</button>
																<button class="btn btn-danger" name="removeResponse"
																	data-response-id="${response.responseId}"
																	data-bs-toggle="modal"
																	data-bs-target="#deleteReviewResponseConfirmModal"
																	data-product-id="${product.productId}"
																	data-customer-id="${userid}">답변삭제</button>
															</div>
														</div>
													</div>
												</div>
												<br />
												<br />
											</c:forEach>

										</div>
									</div>
								</div>
							</c:if>
							<!-- 관리자만 보이게 할 예정 -->
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="name" var="userId" />

								<c:if test="${userId eq 'admin0' }">
									<div class="mb-3">

										<div class="input-group">
											<div class="form-floating">
												<textarea style="height: 97px" class="form-control"
													id="reveiwResponseTextArea${review.reviewId }"></textarea>
											</div>
											<button name="sendReviewResponseButton"
												class="btn btn-outline-primary"
												data-review-id="${review.reviewId}"
												data-product-id="${review.productId}">답변하기</button>

										</div>
									</div>

								</c:if>
							</sec:authorize>
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<br />

	</c:forEach>
</div>

<!-- 페이지네이션 -->
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<!-- 맨앞 -->
		<c:if test="${pageInfo.currentPageNumber > 1 }">
			<my:review pageNumber="1" productId="${reviewInfo.productId }"
				customerId="${reviewInfo.customerId }">
				<i class="fa-solid fa-angles-left"></i>
			</my:review>
		</c:if>

		<!-- 이전 -->
		<c:if test="${pageInfo.currentPageNumber > 1 }">
			<my:review pageNumber="${pageInfo.currentPageNumber -1}"
				productId="${reviewInfo.productId }"
				customerId="${reviewInfo.customerId }">
				<i class="fa-solid fa-angle-left"></i>
			</my:review>
		</c:if>

		<c:forEach begin="${pageInfo.begin }" end="${pageInfo.end }"
			var="pageNumber">
			<!-- li.page-item>a.page-link -->
			<my:review pageNumber="${pageNumber }"
				productId="${reviewInfo.productId }"
				customerId="${reviewInfo.customerId }">
				${pageNumber }
			</my:review>
		</c:forEach>

		<!-- 다음 버튼 -->
		<c:if test="${pageInfo.nextPageNumber le pageInfo.lastPageNumber }">
			<my:review pageNumber="${pageInfo.currentPageNumber + 1 }"
				productId="${reviewInfo.productId }"
				customerId="${reviewInfo.customerId }">
				<i class="fa-solid fa-angle-right"></i>
			</my:review>
		</c:if>

		<!-- 맨뒤로 -->
		<c:if test="${pageInfo.currentPageNumber < pageInfo.lastPageNumber }">
			<my:review pageNumber="${pageInfo.lastPageNumber }"
				productId="${reviewInfo.productId }"
				customerId="${reviewInfo.customerId }">
				<i class="fa-solid fa-angles-right"></i>
			</my:review>
		</c:if>
	</ul>
</nav>


<!-- 리뷰 삭제 Modal -->
<div class="d-none">
	<form action="/product/review/remove" method="post"
		id="removeReviewForm">
		<input type="text" id="removeReviewById" name="reviewId" value="" />
		<input type="text" name="productId" value="${review.productId}" />
	</form>
</div>

<div class="modal fade" id="deleteReviewConfirmModal" tabindex="-1"
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
				<button type="submit" class="btn btn-outline-danger"
					form="removeReviewForm">삭제</button>
				<button type="button" class="btn btn-outline-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<!-- 리뷰 답변 삭제 Modal -->
<div class="d-none">
	<form action="/product/reviewResponse/remove" method="post"
		id="removeResponseForm">
		<input type="text" id="removeResponseById" name="responseId" value="" />
		<input type="text" name="productId" value="${review.productId}" />
	</form>
</div>

<div class="modal fade" id="deleteReviewResponseConfirmModal"
	tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
				<button type="button" class="btn-outline-close"
					data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">삭제하시겠습니까?</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-outline-danger"
					form="removeResponseForm">삭제</button>
				<button type="button" class="btn btn-outline-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script src="/js/product/review/list.js"></script>