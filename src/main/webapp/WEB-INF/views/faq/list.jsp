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
</head>
<body>
	<my:navBar current="faq" />

	<button id="product" type="button" class="btn btn-outline-primary">상품관련</button>
	<button id="order" type="button" class="btn btn-outline-secondary">주문결제</button>
	<button id="deliver" type="button" class="btn btn-outline-success">배송</button>
	<button id="cancel" type="button" class="btn btn-outline-danger">변경/취소/반품</button>
	<button id="profile" type="button" class="btn btn-outline-warning">회원정보</button>
	<button id="service" type="button" class="btn btn-outline-info">서비스이용</button>

<%-- 	<c:forEach items="${list }" var="faq">
		<p>
			<button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">${faq.category }&nbsp&nbsp&nbsp&nbsp&nbsp${faq.title }</button>
		</p>

		<div class="collapse" id="collapseExample">
			<div class="card card-body">${faq.content }</div>
		</div>
	</c:forEach> --%>

	<div id="list">
		<c:forEach items="${list }" var="faq">
			<c:if test="${faq.category eq '상품관련'}">
				<c:forEach items="${faq.content }" var="faqContent">
					<div>${faqContent }</div>
				</c:forEach>
			</c:if>
		</c:forEach>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="/js/faq/category.js"></script>
</body>
</html>