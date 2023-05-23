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
<title>관리자 상품 관리</title>
</head>
<body>
	<my:navBar></my:navBar>
	<div class="container-lg">
		<div class="d-flex">
			<div class="me-auto">
				<h1>상품 관리</h1>
			</div>
			<div>
				<form action="#" class="d-flex" role="search">
					<div class="input-group">
						<select class="form-select flex-grow-0" style="width: 100px;" name="type" id="">
							<option value="all">전체</option>
							<option value="title">상품명</option>
							<option value="body">원산지</option>
							<option value="writer">카테고리</option>
							<option value="writer">재고</option>
						</select>
						<input value="1" name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">
							<i class="fa-solid fa-magnifying-glass"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>상품ID</th>
					<th>사진</th>
					<th>상품명</th>
					<th>원산지</th>
					<th>재고</th>
					<th>카테고리</th>
					<th>가격</th>
					<th>선택</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productList}" var="product">
					<tr>
						<td>${product.productId }</td>
						<td>
							<img alt="${product.fileName[0]}" src="/img/product/${product.fileName[0]}" width="100" height="100">
						</td>
						<td>${product.productName}</td>
						<td>${product.countryOfOrigin}</td>
						<td>${product.stockQuantity}</td>
						<td>${product.categoryName}</td>
						<td>${product.price}</td>
						<td>
							<a class="btn btn-secondary" href="/admin/product/detail?id=${product.productId}">상세보기</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>