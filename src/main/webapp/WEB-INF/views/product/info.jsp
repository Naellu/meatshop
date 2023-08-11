<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<style>
.tab {
	overflow: hidden;
}

.tab button {
	background-color: #f2f2f2;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 10px 20px;
	transition: background-color 0.3s;
}

.tab button:hover {
	background-color: #ddd;
}

.tab button.active {
	background-color: #ccc;
}

.tabcontent {
	padding: 20px;
	border: 1px solid #ccc;
}
</style>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
</head>
<body>
	<my:navBar />

	<sec:authentication property="name" var="userid" />

	<my:alert />

	<!-- Product section-->
	<section class="bg-dark">
		<div class="container-lg px-4 px-lg-5 text-white">
			<div class="row gx-4 gx-lg-5 align-items-center">
				<div class="col">
					<img class="card-img-top mb-5 mb-md-0" src="${bucketUrl}/product/${product.productId}/main.png" alt="" width="600" height="600" />
				</div>
				<div class="col-md-6">
					<div class="small mb-1">상품번호 - ${product.productId}</div>
					<h1 class="display-5 fw-bolder">${product.productName}</h1>
					<div class="fs-5 mb-5">
						<fmt:formatNumber value="${product.price}" type="number" pattern="0" var="formattedPrice" />
						<span>${formattedPrice}원</span>
					</div>
					<p class="lead">
						원산지 : ${product.countryOfOrigin }
						<br />
						${product.categoryName }
					</p>

					<div class="d-flex mb-4">
						<div class="me-3">옵션</div>
						<button class="minus-btn me-1 btn btn-secondary">
							<i class="fa-solid fa-minus"></i>
						</button>
						<input type="number" id="count" class="form-control number-input" style="max-width: 4rem" value="1" size="100">
						<button class="plus-btn ms-1 btn btn-secondary">
							<i class="fa-solid fa-plus"></i>
						</button>
					</div>
					<div class="d-flex">
						<input type="hidden" id="productId" value="${product.productId}" />
						<input type="hidden" id="stockQuantity" value="${product.stockQuantity }" />
						<input type="hidden" id="price" value="${product.price }" />
						<input type="hidden" id="categoryId" value="${product.categoryId }" />
						<input type="hidden" id="bucketUrl" value="${bucketUrl}" />
						<button class="btn btn-success me-3" id="orderButton">
							<i class="fa-solid fa-credit-card"></i>
							바로주문하기
						</button>
						<button class="btn btn-danger" id="goToCartBtn">
							<i class="fa-solid fa-cart-shopping"></i>
							장바구니에 담기
						</button>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="container mt-4">
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link active" data-toggle="tab" id="productdetail">상품상세</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="tab" id="productInquiry" data-product-id="${product.productId}" data-customer-id="${userid }">상품문의</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="tab" id="productReview" data-product-id="${product.productId}" data-customer-id="${userid }">상품리뷰</a>
			</li>
		</ul>
		<div class="tab-content mt-2">
			<div id="content" class="tab-pane fade show active">
				<img src="${bucketUrl}/product/detail/${product.categoryId}.jpg" alt="사진준비중">
			</div>
		</div>
	</div>

	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/product/product.js"></script>

</body>
</html>