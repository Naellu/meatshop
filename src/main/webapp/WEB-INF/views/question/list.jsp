<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
		<div>
			<h1>1:1 문의하기</h1>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#questionModal">문의하기</button>
			<sec:authorize access="hasAuthority('admin')">
				<form action="/question/list" method="get">
					<select id="searchSelect" name="search">
						<option value="">전체</option>
						<option value="answered">답변 완료</option>
						<option value="unanswered">미답변</option>
					</select>
					<input type="submit" value="검색">
				</form>
			</sec:authorize>
		</div>
	</div>


	<!-- 날짜, 제목, 답변 여부 -->

	<div class="container-lg">
		<table class="table">
			<thead>
				<tr>
					<th>제목</th>
					<th>답변 여부</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${questionList}" var="question">
					<sec:authorize access="authentication.name eq #question.customerId or hasAuthority('admin')">
						<tr>
							<td>
								<a href="/question/id/${question.id}">${question.title}</a>
							</td>
							<td>${question.answered ? '답변 완료' : '미답변'}</td>
							<td>${question.inserted}</td>
						</tr>
					</sec:authorize>
				</c:forEach>
			</tbody>
		</table>
	</div>




	<div class="container-lg">
		<div class="row">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">

					<!-- 이전 버튼 -->
					<c:if test="${pageInfo.currentPageNum gt 1}">
						<my:questionPageInfo pageNum="${pageInfo.currentPageNum - 1}">
							<i class="fa-solid fa-angle-left"></i>
						</my:questionPageInfo>
					</c:if>

					<c:forEach begin="${pageInfo.leftPageNum}" end="${pageInfo.rightPageNum}" var="pageNum">
						<my:questionPageInfo pageNum="${pageNum}">
							${pageNum}
						</my:questionPageInfo>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${pageInfo.currentPageNum lt pageInfo.lastPageNum}">

						<my:questionPageInfo pageNum="${pageInfo.currentPageNum + 1}">
							<i class="fa-solid fa-angle-right"></i>
						</my:questionPageInfo>

					</c:if>

				</ul>
			</nav>
		</div>
	</div>


	<!-- 모달 -->

	<div class="modal fade" id="questionModal" role="dialog" tabindex="-1" aria-labelledby="questionModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="questionModalLabel">문의하기</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="/question/add" method="post" enctype="multipart/form-data">
						<div class="mb-3">
							<label for="titleInput" class="form-label">제목</label>
							<input id="titleInput" class="form-control" type="text" name="title" value="${question.title }" />
						</div>
						<div class="mb-3">
							<label for="contextTextarea" class="form-label">본문</label>
							<textarea rows="10" id="contentTextarea" class="form-control" name="content">${question.content }</textarea>
						</div>

						<div class="mb-3">
							<label for="fileInput" class="form-label">그림 파일</label>
							<input class="form-control" type="file" id="fileInput" name="files" accept="image/*" multiple>
							<div class="form-text">총 10MB, 하나의 파일은 1MB를 초과할 수 없습니다.</div>
						</div>

						<div class="mb-3">
							<input class="btn btn-primary" type="submit" value="등록" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="/js/question/questionList.js"></script>
</body>
</html>