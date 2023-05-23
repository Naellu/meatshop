<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %><!-- 태그이용을 위한 링크 -->
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- spring security 라이브러리를 사용하기위한 태그 -->
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags"%><!-- 내가 만든 태그 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"><!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" /><!--fontawsome-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"><!-- 부트 스트랩 -->
<title>Insert title here</title>
</head>
<body>

<div class="container-lg">

		<div class="row justify-content-center"></div>
			<div class="col-12 col-md-8 col-lg-6"></div>
			
			<h1>${member.id }님 회원정보</h1>
		
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<h1>회원 정보</h1>
				<!-- .mb-3*4>label+input -->
				<div class="mb-3">
					<label class="form-label" for="">아이디</label>
					<input class="form-control" type="text" value="${member.id }" readonly/>
				</div>
				<div class="mb-3">
					<label class="form-label" for="">패스워드</label>
					<input class="form-control" type="text" value="${member.member_password }" readonly/>
				</div>
				
				<div class="mb-3">
					<label class="form-label" for="">이메일</label>
					<input class="form-control" type="text" value="${member.member_email }" readonly />
				</div>
		
		<!-- <sec:authorize access="authentication.name eq #member.id">  -->	
				<a class="btn btn-secondary" href="/member/modify?id=${member.id }">수정</a>
				<button type="button" data-bs-toggle="modal" class="btn btn-danger" data-bs-target="#confirmModal">탈퇴</button>
			</sec:authorize>
			</div>
		</div>
	</div>
	</div>
	
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script><!-- 부트 스트랩 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script><!--jquery-->
</body>
</html>