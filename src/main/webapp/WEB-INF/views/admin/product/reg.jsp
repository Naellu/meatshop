<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 추가</title>
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
						<h1>상품등록</h1>
					</div>
				</div>
				<form method="post" enctype="multipart/form-data">
					<div>
						<div class="mb-3">
							<label for="" class="form-label">상품명</label>
							<input type="text" id="productNameInput" name="productName" class="form-control" />
						</div>

						<div class="mb-3">
							<label for="" class="form-label">원산지</label>
							<select class="form-select" aria-label="Default select example" name="countryOfOrigin">
								<option value="국내산">국내산</option>
								<option value="미국산">미국산</option>
								<option value="호주산">호주산</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="categoryId" class="form-label">종류</label>
							<select class="form-select" aria-label="Default select example" name="categoryId">
								<option value="1">소</option>
								<option value="2">돼지</option>
								<option value="3">닭</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="" class="form-label">재고</label>
							<input type="number" id="stockQuantityInput" name="stockQuantity" class="form-control" />
						</div>

						<div class="mb-3">
							<label for="" class="form-label">가격</label>
							<input type="number" id="priceInput" name="price" class="form-control" />
							<div class="form-text">소수점 두자리까지 가능</div>
						</div>

						<div class="mb-3">
							<label for="" class="form-label">공개</label>
							<select class="form-select" aria-label="Default select example" name="pub">
								<option value="1">공개</option>
								<option value="0" selected>비공개</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="formFile" class="form-label">첨부 파일</label>
							<input class="form-control" name="files" type="file" id="formFile" accept="image/*" multiple>
							<div class="form-text">기본사진은 main.png, 상품설명사진은 detail.png로 작성해주세요</div>
							<div class="form-text">총 10MB, 하나의 파일을 1MB를 초과할 수 없습니다.</div>
						</div>

						<div>
							<input class="btn btn-primary" id="addBtn" disabled type="submit" value="상품추가하기" />
							<a class="btn btn-secondary" href="/admin/product/list">상품관리</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/admin/product/reg.js"></script>
</body>
</html>