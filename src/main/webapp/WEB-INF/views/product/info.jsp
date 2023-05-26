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
</head>
<body>
	<my:navBar />

	<my:alert />
	<h1>커스터머 상품상세페이지</h1>
	<div class="container-lg">
		<!-- .row.justify-content-center>.col-12.col-md-8.col-lg-6 -->
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<div class="d-flex">
					<div class="me-auto">
						<h1>
							<span id="boardIdText"> ${product.productId} </span>
							번 상품
						</h1>
					</div>
				</div>
				<div class="d-flex">
					<div class="me-auto">
						<img src="/img/product/1.png" alt="" />
					</div>

					<div>
						<div class="mb-3">
							<label for="" class="form-label">상품명</label>
							<input type="text" class="form-control" value="${product.productName}" readonly />
						</div>
						<%-- 
					<div class="mb-3">
						<c:forEach items="${product.fileName}" var="fileName">
							<div>
								<img class="img-thumbnail img-fluid " src="${bucketUrl}/product/${product.productId}/${fileName}" alt="${fileName}" />
							</div>
						</c:forEach>
					</div> --%>

						<div class="mb-3">
							<label for="" class="form-label">원산지</label>
							<input type="text" class="form-control" value="${product.countryOfOrigin }" readonly />
						</div>

						<div class="mb-3">
							<label for="count" class="form-label">구매량</label>
							<button class="plus-btn">+</button>
							<input type="number" id="count" class="number-input" value="0">
							<button class="minus-btn">-</button>
						</div>

						<div class="mb-3">
							<label for="" class="form-label">종류</label>
							<input type="text" class="form-control" value="${product.categoryName }" readonly />
						</div>

						<div class="mb-3">
							<label for="" class="form-label">가격</label>
							<input type="text" class="form-control" value="${product.price}" readonly />
						</div>

						<div>
							<input type="hidden" id="productId" value="${product.productId}" />
							<input type="hidden" id="stockQuantity" value="${product.stockQuantity }" />
							<input type="hidden" id="price" value="${product.price }" />
							<button id="orderButton">바로주문하기</button>
							<button id="goToCartBtn">장바구니로가기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-12 col-md-8 col-lg-6">
			<h1>상품 상세설명란</h1>
		</div>
	</div>



	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/customer/product.js"></script>
</body>
</html>