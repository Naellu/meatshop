<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %><!-- 태그이용을 위한 링크 -->
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- spring security 라이브러리를 사용하기위한 태그 -->
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags"%><!-- 내가 만든 태그 -->
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"><!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" /><!--fontawsome-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"><!-- 부트 스트랩 -->
<my:commonFont></my:commonFont>
<title>회원목록</title>
</head>
<body>
<my:navBar></my:navBar>

<my:alert />

<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<!-- 테이블 크기조절 -->
				<h1>회원 목록</h1>
					<form action = "list" class="d-flex" role="search">
		<select class="form-select flex-grow-0" name = "type" id= "" style = "width : 100px;">
			<option value= "all">전체</option>
			<option value= "name" ${param.type eq 'name' ? 'selected' : '' }>이름</option>
			<option value= "birthday" ${param.type eq 'birthday' ? 'selected' : '' }>생일</option>
			<option value= "phoneNumber" ${param.type eq 'phoneNumber' ? 'selected' : '' }>핸드폰 번호</option>
			</select>
				<input value="${param.search }" name="search" class="form-control me-2" type="search" placeholder="Search" aria-label="Search" >
				<button class="btn btn-outline-success" type="submit">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>PASSWORD</th>
							<th>이름</th>
							<th>이메일</th>
							<th>가입일시</th>
						</tr>
					</thead>
					
	


					<tbody>
						<c:forEach items="${memberList}" var="member">
							<tr>
								<c:url value="/member/info" var="memberInfoLink">
									<c:param name="id" value="${member.id}" />
								</c:url>
								<td><a href="${memberInfoLink}">${member.id}</a>
								
								
								
								</td>
								<td>${member.password}</td>
								<td>${member.name}</td>
								<td>${member.email}</td>
								<td>${member.created}</td>
								
							</tr>
						</c:forEach>



					</tbody>


				</table>
			</div>
		</div>
	</div>
	
	
	
	
	<div class="container-lg">
		<div class="row">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">

					<!-- 이전 버튼 -->
					<c:if test="${pageInfo.currentPageNum gt 1 }">
						<c:url value="/member/list" var="pageLink">
							<c:param name="page" value="${pageInfo.currentPageNum - 1 }" />
							<c:param name="search" value="${param.search }" />
								<c:if test="${not empty param.type }">
									<c:param name="type" value="${param.type }"/>
							</c:if>
						</c:url>
						<li class="page-item">
							<a class="page-link" href="${pageLink }">이전
								<i class="fa-solid fa-angle-left"></i>
							</a>
						</li>
					</c:if>
				<!-- 페이지 버튼 -->
					<c:forEach begin="${pageInfo.leftPageNum }" end="${pageInfo.rightPageNum }" var="pageNum">
						<c:url value="/member/list" var="pageLink">
							<c:param name="page" value="${pageNum }" />
							<c:if test="${not empty param.search }">
							<c:param name="search" value="${param.search }" />
							</c:if>
							<c:if test="${not empty param.type }">
							
							<c:param name="type" value="${param.type }"/>
							</c:if>
							
						</c:url>
						<li class="page-item">
							<a class="page-link ${pageNum eq pageInfo.currentPageNum ? 'active' : '' }" href="${pageLink }">${pageNum }</a>
						</li>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${pageInfo.currentPageNum < pageInfo.lastPageNum }">
						<c:url value="/member/list" var="pageLink">
							<c:param name="page" value="${pageInfo.currentPageNum + 1 }" />
							<c:if test="${not empty param.search }">
								<c:param name="search" value="${param.search }" />
							</c:if>
									<c:if test="${not empty param.type }">
									<c:param name="type" value="${param.type }"/>
							</c:if>
						</c:url>
						<li class="page-item">
							<a class="page-link" href="${pageLink }">다음
								<i class="fa-solid fa-angle-right"></i>
							</a>
						</li>
					</c:if>

				</ul>
			</nav>
		</div>	
	</div>
	

	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script><!-- 부트 스트랩 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script><!--jquery-->
</body>
</html>