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
<title>로그인페이지</title>
</head>
<body>
	<my:navBar></my:navBar>

	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<h1>로그인</h1>
				<form method="post">
					<div class="mb-3">
						<label for="inputUsername" class="form-label"> 아이디</label> <input id="inputUsername" class="form-control" type="text" name="username" />
					</div>
					<div class="mb-3">
						<label for="inputPassword" class="form-label">암호</label> <input id="inputPassword" class="form-control" type="password" name="password" />
					</div>
					<input class="btn btn-primary"  type="submit" value="로그인" />
				</form>
				<div>
					<a class="p-2" href="https://kauth.kakao.com/oauth/authorize?client_id=d7f3b956bef7e076113cc3f6f070b65b&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code "> <img src="/img/kakao/kakao.png" style="height: 50px">
					</a>
				</div>
			</div>
		</div>
	</div>



	<hr>
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<div class="mb-3">
					<a href="/member/signup" class="btn btn-info">회원가입</a>
					 <a href="/member/searchId" class="btn btn-info">아이디 찾기</a>
					  <a href="/member/searchPassword" class="btn btn-info">비밀번호찾기 찾기</a>
				</div>
			</div>
		</div>
	</div>






	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<!-- 부트 스트랩 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!--jquery-->

</body>


</html>