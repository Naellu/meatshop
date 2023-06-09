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
	<my:navBar current="nbList" />
	<div class="container-lg">
		<sec:authorize access="hasAuthority('admin')">
			<button type="button" class="btn btn-primary" onclick="location.href='/noticeBoard/add'">공지사항 작성</button>
		</sec:authorize>
		<div class="team-grid">
			<div class="container">
				<div class="intro">
					<h2 class="text-center">공지사항</h2>
				</div>
			</div>
		</div>
	</div>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Team Member 1 -->
			<c:forEach items="${noticeBoardList }" var="nboard">
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-0 shadow">
						<!-- <img src="https://source.unsplash.com/TMgQMXoglsM/500x350" class="card-img-top" alt="..."> -->
						<div class="card-body text-center">
							<h5 class="card-title mb-0" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
								<a href="/noticeBoard/id/${nboard.id }" style="text-decoration: none"> ${nboard.title } </a>
							</h5>
							<div class="card-text text-black-50">${nboard.inserted }</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<%-- 	<a href="/noticeBoard/id/${nboard.id }"> ${nboard.title } </a>
		<p class="title">${nboard.writer }</p>
		<p class="title">${nboard.inserted }</p>
		</div> --%>

	<%-- 	<div class="container-lg">
		<!-- table.table>thead>tr>th*4^^tbody -->

		<table class="table">
			<thead>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noticeBoardList }" var="nboard">
					<tr>
						<td>
							<a href="/noticeBoard/id/${nboard.id }"> ${nboard.title } </a>
						</td>
						<td>${nboard.writer }</td>
						<td>${nboard.inserted }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> --%>
	
	<div style="margin-bottom: 30px"></div>

	<div class="container-lg">
		<div class="row">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">

					<!-- 이전 버튼 -->
					<c:if test="${pageInfo.currentPageNum gt 1 }">
						<my:noticeBoardPageInfo pageNum="${pageInfo.currentPageNum - 1 }">
							<i class="fa-solid fa-angle-left"></i>
						</my:noticeBoardPageInfo>
					</c:if>

					<c:forEach begin="${pageInfo.leftPageNum }" end="${pageInfo.rightPageNum }" var="pageNum">
						<my:noticeBoardPageInfo pageNum="${pageNum }">
							${pageNum }
						</my:noticeBoardPageInfo>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${pageInfo.currentPageNum lt pageInfo.lastPageNum }">

						<my:noticeBoardPageInfo pageNum="${pageInfo.currentPageNum + 1 }">
							<i class="fa-solid fa-angle-right"></i>
						</my:noticeBoardPageInfo>

					</c:if>

				</ul>
			</nav>
		</div>
	</div>

	
	<my:footer />

	<!-- <script src="/js/noticeBoard/list.js"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>