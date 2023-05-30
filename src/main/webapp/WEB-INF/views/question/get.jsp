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

	<my:alert />

	<!-- toast -->
	<div class="toast-container position-fixed top-0 start-50 translate-middle-x p-3">
		<div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
			<div class="d-flex">
				<div class="toast-body"></div>
				<button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
			</div>
		</div>
	</div>

	<!-- 글 읽기 -->
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<div class="d-flex">
					<div class="me-auto">
						<div class="d-none">
							<h1>
								<span id="questionIdText"> ${question.id}</span>
							</h1>
						</div>
						<div class="mb-3">
							<label for="titleInput" class="form-label">제목</label>
							<input class="form-control" id="titleInput" type="text" name="title" value="${question.title }" />
						</div>


						<!-- 그림 파일 출력 -->
						<div class="mb-3">
							<c:forEach items="${question.fileName }" var="fileName">
								<div class="mb-3">
									<img class="img-thumbnail img-fluid" src="${bucketUrl }/question/${question.id }/${fileName}" alt="" />
								</div>
							</c:forEach>
						</div>

						<div class="mb-3">
							<label for="" class="form-label">본문</label>
							<textarea class="form-control" readonly rows="10">${question.content }</textarea>
						</div>

						<div class="mb-3">
							<label for="" class="form-label">작성일시</label>
							<input type="text" readonly class="form-control" value="${question.inserted }" />
						</div>

						<div id="answerContainer">
							<h1>답변</h1>
							<div class="mb-3" id="addAnswerContainer">
								<div class="input-group">
									<div class="form-floating">
										<textarea style="height: 97px" placeholder="답변을 남겨주세요" class="form-control" id="answerTextArea"></textarea>
										<label for="floatingTextarea">답변을 남겨주세요</label>
									</div>
									<button class="btn btn-outline-primary" id="sendAnswerBtn"><i class="fa-regular fa-paper-plane"></i></button>
								</div>
							</div>
							
							<ul class="list-group" id="answerListContainer">
							
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 수정, 삭제 -->
	<a class="btn btn-secondary" href="/question/modify/${question.id }">수정</a>
	<button type="button" class="btn btn-danger" form="removeForm" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">삭제</button>

	<!-- 삭제 -->
	<div class="d-none">
		<form action="/question/remove" method="post" id="removeForm">
			<input type="text" name="id" value="${question.id }" />
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
					<button type="submit" class="btn btn-danger" form="removeForm">삭제</button>
				</div>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>