<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
</head>
<body>
	<my:navBar current="faq" />
	
	<div class="container-lg">

		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<h1>FAQ 작성</h1>
				<form method="post">
					<div class="mb-3">
						<label for="titleInput" class="form-label">제목</label>
						<input id="titleInput" class="form-control" type="text" name="title" value="${faq.title }" />
					</div>
					<div class="mb-3">
						<label for="contextTextarea" class="form-label">본문</label>
						<textarea rows="10" id="contentTextarea" class="form-control" name="content">${faq.content }</textarea>
					</div>
					
					<div class="mb-3">
						<label for="categoryInput" class="form-label">카테고리</label>
						<select id="categoryInput" name="category">
							<option value="상품관련">상품관련</option>
							<option value="주문결제">주문결제</option>
							<option value="배송">배송</option>
							<option value="변경/취소/반품">변경/취소/반품</option>
							<option value="회원정보">회원정보</option>
							<option value="서비스이용">서비스이용</option>
						</select>
					</div>
					
					<div class="mb-3">
						<input class="btn btn-primary" type="submit" value="등록" />
					</div>
				</form>
			</div>
		</div>
	</div>

	<my:footer/>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>