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
							<h1 id="titleInput">${question.title }</h1>
							<a href="/question/list">목록 보기</a>
						</div>
						<hr class="long-hr">

						<!-- 그림 파일 출력 -->
						<div class="mb-3">
							<c:forEach items="${question.fileName }" var="fileName">
								<div class="mb-3">
									<img class="img-thumbnail img-fluid" src="${bucketUrl }/question/${question.id }/${fileName}" alt="" />
								</div>
							</c:forEach>
						</div>

						<div class="mb-3">
							<label for="insertedInput" class="form-label">작성일시</label>
							<input type="text" id="insertedInput" readonly class="form-control" value="${question.inserted }" />
						</div>

						<div class="mb-3">
							본문:
							<h3>${question.content }</h3>
						</div>


						<div id="answerContainer">
							<h1>답변</h1>
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

	<my:footer/>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<script src="/js/answer/answer.js"></script>
</body>
</html>