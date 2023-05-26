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

	<div id="list1">
		<c:forEach items="${faq}" var="faq">
			<c:if test="${faq.category eq '상품관련'}">
				<div class="accordion">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${faq.id}" aria-expanded="false" aria-controls="collapse-${faq.id}">
								<h3>Q</h3> <br />${faq.title}
							</button>
						</h2>
						<div id="collapse-${faq.id}" class="accordion-collapse collapse" aria-labelledby="heading-${faq.id}">
							<div class="accordion-body">${faq.content}</div>
						</div>
						<!-- 수정, 삭제 -->
						<%-- <a class="btn btn-secondary" href="/faq/modify/${faq.id }">수정</a> --%>
						<button id="removeButton-${faq.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
					</div>
				</div>
				<!-- 삭제 -->
				<div class="d-none">
					<form action="/faq/remove" method="post" id="removeForm-${faq.id}">
						<input type="text" name="id" value="${faq.id}" />
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
				<div class="modal-body">삭제 하시겠습니까?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					<button type="submit" class="btn btn-danger" form="removeForm-${faq.id}">삭제</button>
				</div>
			</div>
		</div>
	</div>
			</c:if>
		</c:forEach>
	</div>

	<div id="list2">
		<c:forEach items="${faq }" var="faq">
			<c:if test="${faq.category eq '주문결제'}">
				<div class="accordion">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${faq.id}" aria-expanded="false" aria-controls="collapse-${faq.id}">
								<h3>Q</h3> <br />${faq.title}
							</button>
						</h2>
						<div id="collapse-${faq.id}" class="accordion-collapse collapse" aria-labelledby="heading-${faq.id}">
							<div class="accordion-body">${faq.content}</div>
						</div>
						<!-- 수정, 삭제 -->
						<%-- <a class="btn btn-secondary" href="/faq/modify/${faq.id }">수정</a> --%>
						<button id="removeButton-${faq.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
					</div>
				</div>
				<!-- 삭제 -->
				<div class="d-none">
					<form action="/faq/remove" method="post" id="removeForm-${faq.id}">
						<input type="text" name="id" value="${faq.id}" />
					</form>
				</div>

			</c:if>
		</c:forEach>
	</div>

	<div id="list3">
		<c:forEach items="${faq }" var="faq">
			<c:if test="${faq.category eq '배송'}">
				<div class="accordion">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${faq.id}" aria-expanded="false" aria-controls="collapse-${faq.id}">
								<h3>Q</h3> <br />${faq.title}
							</button>
						</h2>
						<div id="collapse-${faq.id}" class="accordion-collapse collapse" aria-labelledby="heading-${faq.id}">
							<div class="accordion-body">${faq.content}</div>
						</div>
						<!-- 수정, 삭제 -->
						<%-- <a class="btn btn-secondary" href="/faq/modify/${faq.id }">수정</a> --%>
						<button id="removeButton-${faq.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
					</div>
				</div>
				<!-- 삭제 -->
				<div class="d-none">
					<form action="/faq/remove" method="post" id="removeForm-${faq.id}">
						<input type="text" name="id" value="${faq.id}" />
					</form>
				</div>
			</c:if>
		</c:forEach>
	</div>

	<div id="list4">
		<c:forEach items="${faq }" var="faq">
			<c:if test="${faq.category eq '변경/취소/반품'}">
				<div class="accordion">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${faq.id}" aria-expanded="false" aria-controls="collapse-${faq.id}">
								<h3>Q</h3> <br />${faq.title}
							</button>
						</h2>
						<div id="collapse-${faq.id}" class="accordion-collapse collapse" aria-labelledby="heading-${faq.id}">
							<div class="accordion-body">${faq.content}</div>
						</div>
						<!-- 수정, 삭제 -->
						<%-- <a class="btn btn-secondary" href="/faq/modify/${faq.id }">수정</a> --%>
						<button id="removeButton-${faq.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
					</div>
				</div>
				<!-- 삭제 -->
				<div class="d-none">
					<form action="/faq/remove" method="post" id="removeForm-${faq.id}">
						<input type="text" name="id" value="${faq.id}" />
					</form>
				</div>
			</c:if>
		</c:forEach>
	</div>

	<div id="list5">
		<c:forEach items="${faq }" var="faq">
			<c:if test="${faq.category eq '회원정보'}">
				<div class="accordion">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${faq.id}" aria-expanded="false" aria-controls="collapse-${faq.id}">
								<h3>Q</h3> <br />${faq.title}
							</button>
						</h2>
						<div id="collapse-${faq.id}" class="accordion-collapse collapse" aria-labelledby="heading-${faq.id}">
							<div class="accordion-body">${faq.content}</div>
						</div>
						<!-- 수정, 삭제 -->
						<%-- <a class="btn btn-secondary" href="/faq/modify/${faq.id }">수정</a> --%>
						<button id="removeButton-${faq.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
					</div>
				</div>
				<!-- 삭제 -->
				<div class="d-none">
					<form action="/faq/remove" method="post" id="removeForm-${faq.id}">
						<input type="text" name="id" value="${faq.id}" />
					</form>
				</div>
			</c:if>
		</c:forEach>
	</div>

	<div id="list6">
		<c:forEach items="${faq }" var="faq">
			<c:if test="${faq.category eq '서비스이용'}">
				<div class="accordion">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${faq.id}" aria-expanded="false" aria-controls="collapse-${faq.id}">
								<h3>Q</h3> <br />${faq.title}
							</button>
						</h2>
						<div id="collapse-${faq.id}" class="accordion-collapse collapse" aria-labelledby="heading-${faq.id}">
							<div class="accordion-body">${faq.content}</div>
							<!-- 수정, 삭제 -->
						<%-- <a class="btn btn-secondary" href="/faq/modify/${faq.id }">수정</a> --%>
						<button id="removeButton-${faq.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>
					</div>
				</div>
				<!-- 삭제 -->
				<div class="d-none">
					<form action="/faq/remove" method="post" id="removeForm-${faq.id}">
						<input type="text" name="id" value="${faq.id}" />
					</form>
				</div>
			</c:if>
		</c:forEach>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">삭제 하시겠습니까?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					<button type="submit" class="btn btn-danger" form="removeForm}">삭제</button>
				</div>
			</div>
		</div>
	</div>

	

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="/js/faq/category.js"></script>
</body>
</html>