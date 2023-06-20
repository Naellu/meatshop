<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
</head>
<body>
	<my:adminNavBar />

	<my:alert />

	<div class="container-lg">
		<!-- .row.justify-content-center>.col-12.col-md-8.col-lg-6 -->
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<div class="d-flex">
					<div class="me-auto">
						<h1>
							<span id="boardIdText"> ${product.productId} </span>
							번 상품
							<input type="hidden" id="productId" value="${product.productId}" />
						</h1>
					</div>
				</div>
				<div>
					<div class="mb-3">
						<label for="" class="form-label">상품명</label>
						<input type="text" class="form-control" value="${product.productName}" readonly />
					</div>

					<div class="mb-3">
						<c:forEach items="${product.fileName}" var="fileName">
							<div>
								<img class="img-thumbnail img-fluid " src="${bucketUrl}/product/${product.productId}/${fileName}" alt="${fileName}" />
							</div>
						</c:forEach>
					</div>

					<div class="mb-3">
						<label for="" class="form-label">원산지</label>
						<input type="text" class="form-control" value="${product.countryOfOrigin }" readonly />
					</div>

					<div class="mb-3">
						<label for="" class="form-label">재고</label>
						<input type="text" class="form-control" value="${product.stockQuantity }" readonly />
					</div>

					<div class="mb-3">
						<label for="" class="form-label">종류</label>
						<input type="text" class="form-control" value="${product.categoryName }" readonly />
					</div>

					<div class="mb-3">
						<label for="" class="form-label">가격</label>
						<input type="text" class="form-control" value="${product.price}" readonly />
					</div>

					<div class="mb-3">
						<label for="" class="form-label">공개</label>
						<input type="text" class="form-control" value="${product.pub eq 1 ? '공개' : '비공개'}" readonly />
					</div>

					<div>
						<a class="btn btn-primary" href="/admin/product/modify/${product.productId}">수정</a>
						<button id="removeButton" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
						<a class="btn btn-secondary" href="/admin/product/list">상품관리</a>
						<button id="notificationBtn" class="btn btn-success">재입고 알림 보내기</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="d-none">
		<form action="/admin/product/remove" method="post" id="removeForm">
			<input type="text" name="id" value="${product.productId }" />
		</form>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">상품을 삭제 하시겠습니까?</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-danger" form="removeForm">삭제</button>
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/admin/product/detail.js"></script>
</body>
</html>