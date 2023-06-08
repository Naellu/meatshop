<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authentication property="name" var="userid" />

<button class="btn btn-primary" id="addReview"
	data-product-id="${review.productId}" data-customer-id="${userid }">리뷰작성하기</button>
<br />
<div class="accordion " id="accordionExample">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">고객ID</th>
				<th scope="col">리뷰 내용</th>
				<th scope="col">평점</th>
				<th scope="col">수정</th>
				<th scope="col">삭제</th>
				<th scope="col">작성시각</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reviewList}" var="review">
				<tr>
					<td>${review.customerId}</td>
					<td>${review.content}</td>
					<td>${review.rating}</td>
					<td>
						<button class="btn btn-primary" name="modifyReview"
							data-review-id="${review.reviewId}" data-customer-id="${userid }">${review.reviewId }수정</button>
					</td>
					<td>
						<button class="btn btn-danger" name="removeReview"
							data-review-id="${review.reviewId}"
							data-product-id="${review.productId}"
							data-customer-id="${userid }" data-bs-toggle="modal"
							data-bs-target="#deleteReviewConfirmModal"
							data-product-id="${product.productId}"
							data-customer-id="${userid }">${review.reviewId}삭제</button>

					</td>
					<td>${review.createdAt}</td>
				</tr>
				<tr>
					<td colspan=6>
						<div class="mb-3">
							<c:forEach items="${review.fileNames }" var="fileName">
								<img class="img-thumbnail img-fluid"
									src="${bucketUrl }/review/${review.reviewId }/${fileName}"
									alt="" />
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan=6>
						<div class="mb-3" id="answerContainer${review.reviewId }">
							<!-- 답변이 표시될 구역 -->


						</div> <!-- 관리자만 보이게 할 예정 -->
						<div class="mb-3">

							<div class="input-group">
								<div class="form-floating">
									<textarea style="height: 97px" class="form-control"
										id="reveiwAnswerTextArea${review.reviewId }"></textarea>
								</div>
								<button name="sendAnswerButton" class="btn btn-outline-primary"
									data-review-id="${review.reviewId}">답변하기(${review.reviewId })</button>
							</div>
						</div>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>


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
			<c:if
				test="${ pageInfo.currentPageNumber < pageInfo.lastPageNumber }">
				<my:review pageNumber="${pageInfo.lastPageNumber }"
					productId="${reviewInfo.productId }"
					customerId="${reviewInfo.customerId }">
					<i class="fa-solid fa-angles-right"></i>
				</my:review>
			</c:if>
		</ul>
	</nav>
</div>

<div class="d-none">
	<form action="/product/review/remove" method="post" id="removeForm">
		<input type="text" id="removeReviewById" name="reviewId" value="" />
		<input type="text" name="productId" value="${review.productId}" />
	</form>
</div>

<!-- Modal -->
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
					form="removeForm">삭제</button>
				<button type="button" class="btn btn-outline-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script src="/js/product/review/list.js"></script>
