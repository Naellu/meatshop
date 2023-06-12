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
<style>
.long-hr {
	width: 101%;
	height: 2px;
	background-color: black;
}
</style>
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
							<button type="button" class="btn btn-primary" onclick="location.href='/question/list'">목록 보기</button>
							<h1 id="titleInput">${question.title }</h1>
						</div>
						<hr class="long-hr">

						<!-- 그림 파일 출력 -->
						<div class="mb-3">
							<h4>문의 내용:</h4>
							<c:forEach items="${question.fileName }" var="fileName">
								<div class="mb-3">
									<img class="img-thumbnail img-fluid" src="${bucketUrl }/question/${question.id }/${fileName}" alt="" />
								</div>
							</c:forEach>
						</div>
						<div class="mb-3">
							<h2>${question.content }</h2>
						</div>

						<div class="mb-3">
							<label for="insertedInput" class="form-label">작성일시</label>
							<input type="text" id="insertedInput" readonly class="form-control" value="${question.inserted }" />
						</div>



						<div id="answerContainer">
							<sec:authorize access="hasAuthority('admin')">
								<div class="mb-3" id="addAnswerContainer">
									<div class="input-group">
										<div class="form-floating">
											<textarea style="height: 97px" placeholder="답변을 남겨주세요" class="form-control" id="answerTextArea"></textarea>
											<label for="answerTextArea">답변을 남겨주세요</label>
										</div>
										<button class="btn btn-outline-primary" id="sendAnswerBtn"><i class="fa-regular fa-paper-plane"></i></button>
									</div>
								</div>

							</sec:authorize>

							<ul class="list-group" id="answerListContainer">

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<sec:authorize access="hasAuthority('admin')">

		<!-- 댓글 삭제 Modal -->
		<div class="modal fade" id="deleteAnswerConfirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5">답변 삭제 확인</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">삭제 하시겠습니까?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						<button id="deleteAnswerModalButton" data-bs-dismiss="modal" type="submit" class="btn btn-danger">삭제</button>
					</div>
				</div>
			</div>
		</div>

		<%-- 댓글 수정 모달 --%>
		<div class="modal fade" id="answerUpdateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5">답변 수정</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div id="updateAnswerContainer">
							<input type="hidden" id="answerUpdateIdInput" />
							<textarea class="form-control" id="answerUpdateTextArea"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="updateAnswerBtn" data-bs-dismiss="modal">수정</button>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>

	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="/js/answer/answer.js"></script>
</body>
</html>