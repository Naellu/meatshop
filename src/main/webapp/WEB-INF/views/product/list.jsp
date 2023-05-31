<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>상품목록</title>
</head>
<body>
	<my:navBar />

	<my:alert />

	<my:header>
		상 품 목 록
		<input type="hidden" id="bucketUrl" value="${bucketUrl}" />
	</my:header>

	<section class="py-5">
		<div class="container-lg">
			<nav class="navbar navbar-expand-lg bg-body-white">
				<div class="container-fluid bg-white">
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0 nav-tabs nav-pills">
							<li class="nav-item ms-5">
								<a id="allLink" class="nav-link ${empty param.category ? 'active text-white' : '' }">전체</a>
							</li>
							<li class="nav-item">
								<a id="beefLink" class="nav-link ${param.category eq 1 ? 'active text-white' : '' }">소고기</a>
							</li>
							<li class="nav-item">
								<a id="porkLink" class="nav-link ${param.category eq 2 ? 'active text-white' : '' }">돼지고기</a>
							</li>
							<li class="nav-item">
								<a id="chickenLink" class="nav-link ${param.category eq 3 ? 'active text-white' : '' }">닭고기</a>
							</li>
						</ul>

						<div class="d-flex">
							<form id="form1" action="/product/list" class="d-flex" role="search">
								<div class="input-group">
									<select class="form-select flex-grow-0" style="width: 120px;" name="type">
										<option value="all">전체</option>
										<option value="productName" ${param.type eq 'productName' ? 'selected' : ''}>상품명</option>
										<option value="countryOfOrigin" ${param.type eq 'countryOfOrigin' ? 'selected' : ''}>원산지</option>
									</select>
									<c:if test="${not empty param.category}">
										<input type="hidden" name="category" value="${param.category}">
									</c:if>
									<input name="search" class="form-control" type="search" placeholder="Search" aria-label="Search" value="${param.search }">
									<button class="btn btn-outline-success" type="submit">
										<i class="fa-solid fa-magnifying-glass"></i>
									</button>
								</div>
							</form>
							<c:if test="${not empty param}">
								<div class="ms-3">
									<button class="btn btn-outline-danger" type="button" id="cancelSearchButton">
										<i class="fa-solid fa-x"></i>
									</button>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach items="${productList}" var="product" varStatus="status">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<div>
								<img class="card-img-top" src="${bucketUrl}/product/1.png" alt="사진준비중!" />
								<i class="fa-regular fa-star fa-2x" style="position: absolute; top: 0; right: 0;"></i>
							</div>
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${product.productName}</h5>
									<!-- Product price-->
									${product.countryOfOrigin}
									<br />
									${product.price}원
									<br />
									남은수량 : ${product.stockQuantity}
									<span class="badge text-bg-danger ${product.stockQuantity eq 0 ? '' : 'd-none'}">품절</span>
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<button onclick="location.href='/product/info/${product.productId}'" class="btn btn-secondary mt-auto" ${product.stockQuantity eq 0 ? 'disabled' : ''}>상품 상세 보기</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Line break after every 4 products -->
					<c:if test="${status.index % 4 == 3}">
						<div class="w-100"></div>
					</c:if>
				</c:forEach>
			</div>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${pageInfo.currentPageNumber ne 1}">
						<li class="page-item">
							<c:url value="/product/list" var="pageLink">
								<c:param name="page" value="1" />
								<c:if test="${not empty param.type}">
									<c:param name="type" value="${param.type}" />
								</c:if>
								<c:if test="${not empty param.search}">
									<c:param name="search" value="${param.search}" />
								</c:if>
								<c:if test="${not empty param.category}">
									<c:param name="category" value="${param.category}" />
								</c:if>
							</c:url>
							<a class="page-link" href="${pageLink}">
								<i class="fa-solid fa-angles-left"></i>
							</a>
						</li>
					</c:if>

					<c:if test="${pageInfo.currentPageNumber ne 1}">
						<li class="page-item">
							<c:url value="/product/list" var="pageLink">
								<c:param name="page" value="${pageInfo.prevPageNumber}" />
								<c:if test="${not empty param.type}">
									<c:param name="type" value="${param.type}" />
								</c:if>
								<c:if test="${not empty param.search}">
									<c:param name="search" value="${param.search}" />
								</c:if>
								<c:if test="${not empty param.category}">
									<c:param name="category" value="${param.category}" />
								</c:if>
							</c:url>
							<a class="page-link" href="${pageLink}">
								<i class="fa-solid fa-angle-left"></i>
							</a>
						</li>
					</c:if>

					<c:forEach begin="${pageInfo.leftPageNumber}" end="${pageInfo.rightPageNumber }" var="pageNum">
						<li class="page-item">
							<c:url value="/product/list" var="pageLink">
								<c:param name="page" value="${pageNum}" />
								<c:if test="${not empty param.type}">
									<c:param name="type" value="${param.type}" />
								</c:if>
								<c:if test="${not empty param.search}">
									<c:param name="search" value="${param.search}" />
								</c:if>
								<c:if test="${not empty param.category}">
									<c:param name="category" value="${param.category}" />
								</c:if>
							</c:url>
							<a class="page-link ${pageNum eq pageInfo.currentPageNumber ? 'active' : '' }" href="${pageLink}">${pageNum}</a>
						</li>
					</c:forEach>

					<c:if test="${pageInfo.currentPageNumber ne pageInfo.lastPageNumber}">
						<li class="page-item">
							<c:url value="/product/list" var="pageLink">
								<c:param name="page" value="${pageInfo.nextPageNumber}" />
								<c:if test="${not empty param.type}">
									<c:param name="type" value="${param.type}" />
								</c:if>
								<c:if test="${not empty param.search}">
									<c:param name="search" value="${param.search}" />
								</c:if>
								<c:if test="${not empty param.category}">
									<c:param name="category" value="${param.category}" />
								</c:if>
							</c:url>
							<a class="page-link" href="${pageLink}">
								<i class="fa-solid fa-angle-right"></i>
							</a>
						</li>
					</c:if>

					<c:if test="${pageInfo.currentPagjqeNumber ne pageInfo.lastPageNumber}">
						<li class="page-item">
							<c:url value="/product/list" var="pageLink">
								<c:param name="page" value="${pageInfo.lastPageNumber}" />
								<c:if test="${not empty param.type}">
									<c:param name="type" value="${param.type}" />
								</c:if>
								<c:if test="${not empty param.search}">
									<c:param name="search" value="${param.search}" />
								</c:if>
								<c:if test="${not empty param.category}">
									<c:param name="category" value="${param.category}" />
								</c:if>
							</c:url>
							<a class="page-link" href="${pageLink}">
								<i class="fa-solid fa-angles-right"></i>
							</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</section>

	<hr />
	<hr />
	<!-- 연구할 부분 -->
	<div>
		<div class="container px-4 px-lg-5 mt-5">
			<div id="productView" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"></div>
		</div>
		<nav id="productNav" aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
			</ul>
		</nav>
	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/product/list.js"></script>

</body>
</html>