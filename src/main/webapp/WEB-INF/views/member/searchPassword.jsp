
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%><!-- 태그이용을 위한 링크 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- spring security 라이브러리를 사용하기위한 태그 -->
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%><!-- 내가 만든 태그 -->
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!--fontawsome-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<!-- 부트 스트랩 -->
<my:commonFont></my:commonFont>

<meta name="viewport" content="width=device-width, initial-scale=1">


<title>비밀번호 찾기</title>

<style>
.form-control {
	text-align: center;
	margin-bottom: 20px;
}

.passwordfinder {
	text-align: center;
	margin-bottom: 20px;
}
</style>


</head>
<body style="min-height: 100vh; display: flex; flex-direction: column;">
	<my:navBar></my:navBar>

	<div class="container-lg flex-grow-1 ">
		<div class="row justify-content-center">
			<div class="col-lg-6">
				<h1 class="mt-5 passwordfinder">비밀번호 찾기</h1>


				<div class="form-group">
					<label for="email">Email</label> <input type="email" class="form-control" id="email" name="email" placeholder="email 을 입력하세요" required>
				</div>
				<div class="form-group">
					<label for="birthday">Birthday</label> <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Enter your birthday" required>
				</div>
				<div class="d-grid">
					<button type="submit" class="btn btn-primary" id="submitButton">비밀번호 이메일로 전송</button>
				</div>
				<br> <span class="text"> 아이디가 기억나지 않는다면?<a href="searchId" class="link" onclick="idInquiry();"><div class="txt">아이디 찾기</div></a>
				</span>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<!-- 부트 스트랩 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!--jquery-->
	<script src="/js/member/searchPassword.js"></script>
	<my:footer />


</body>
</html>