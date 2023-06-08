<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>

<my:alert></my:alert>

<h1>${review.productId}번상품리뷰</h1>
<hr />
<form action="/product/review/modify" method="post"
	enctype="multipart/form-data">
	<input class="form-control" type="hidden" name="reviewId"	value="${review.reviewId}" /> 
	<input class="form-control" type="hidden" name="productId"	value="${review.productId}" /> 
	<input class="form-control"	type="hidden" name="customerId" value="${review.customerId}" />
	<div class="mb-3 form-floating">
		별점 <select name="rating" id="rating">
			<option value="1" ${review.rating == 1 ? 'selected' : ''}>★</option>
			<option value="2" ${review.rating == 2 ? 'selected' : ''}>★★</option>
			<option value="3" ${review.rating == 3 ? 'selected' : ''}>★★★</option>
			<option value="4" ${review.rating == 4 ? 'selected' : ''}>★★★★</option>
			<option value="5" ${review.rating == 5 ? 'selected' : ''}>★★★★★</option>
		</select> <br /> 리뷰 내용 <br />
		<textarea class="form-control" name="content" rows="10">${review.content }</textarea>

		<!-- 그림 파일 출력 -->
		<div class="mb-3">
			<c:forEach items="${review.fileNames }" var="fileName"
				varStatus="status">
				<div class="mb-3">
					<div class="row">
						<div class="col-2 d-flex">
							<div class="form-check form-switch m-auto">
								<input name="removeFiles" value="${fileName }"
									class="btn-check inpt" type="checkbox" role="switch"
									id="removeCheckBox${status.index }"> <label
									class="btn btn-outline-danger"
									for="removeCheckBox${status.index }"> <i
									class="fa-solid fa-trash-can"></i>
								</label>

							</div>
						</div>

						<div class="col-10">
							<div>
								<img class="img-thumbnail img-fluid"
									src="${bucketUrl }/review/${review.reviewId }/${fileName}" alt="" />
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<br /> 
		사진첨부 <input class="form-control form-control-sm"
			id="formFileSm" type="file" name="files" multiple accept="image/*" />
		<div class="form-text">파일당 1MB, 총합 10MB를 초과할 수 없습니다.</div>
	</div>
	<input class="btn btn-primary" type="submit" value="수정" id="addReview" />
	<button class="btn btn-secondary" type="button" onclick="location.href='/product/info/${review.productId}'" >취소</button>
</form>