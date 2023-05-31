<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%><!-- 태그이용을 위한 링크 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!-- spring security 라이브러리를 사용하기위한 태그 -->
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%><!-- 내가 만든 태그 -->

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
<title>회원가입</title>
</head>
<body>

<my:navBar></my:navBar>

	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">





				<h3>회원 가입</h3>
				<form method="post">
					<!-- .mb-3*5>(label.form-label[for]+input.form-control[name]) -->
					<div class="mb-3">
						<label for="inputId" class="form-label">아이디</label>
						<div class="input-group">
							<input id="inputId" type="text" class="form-control" name="id" value="${members.id }" />
							<button class="btn btn-outline-secondary" type="button" id="checkIdBtn">중복확인</button>
						</div>

						<div class="d-none form-text text-primary" id="availableIdMessage">
							<i class="fa-solid fa-check"></i> 사용 가능한 ID입니다.
						</div>
						<div class="d-none form-text text-danger" id="notAvailableIdMessage">
							<i class="fa-solid fa-triangle-exclamation"></i> 사용 불가능한 ID입니다.
						</div>


					</div>

					<div class="mb-3">
						<label for="inputName" class="form-label">이름</label> <input id="inputName" type="text" class="form-control" name="name" />
					</div>
					<div class="mb-3">
						<label for="inputbirthday" class="form-label">생일</label> <input id="inputbirthday" type="date" class="form-control" name="birthday" />
					</div>



					<div class="mb-3">
						<label for="inputAddress" class="form-label">주소</label> <input id="inputAddress" type="text" class="form-control" name="address" />
					</div>


					<div class="mb-3">
						<label for="inputaddress" class="form-label">전화번호</label> <input id="inputPhoneNumber" type="text" class="form-control" name="phoneNumber" />
					</div>

					<div class="mb-3">
						<label for="inputPassword" class="form-label">패스워드</label> <input id="inputPassword" type="password" class="form-control" name="password" />
					</div>

					<div class="mb-3">
						<label for="inputPasswordCheck" class="form-label">패스워드 확인</label> <input id="inputPasswordCheck" type="password" class="form-control" />

						<div id="passwordSuccessText" class="d-none form-text text-primary">
							<i class="fa-solid fa-check"></i> 패스워드가 일치 합니다.
						</div>

						<div id="passwordFailText" class="d-none form-text text-danger">
							<i class="fa-solid fa-triangle-exclamation"></i> 패스워드가 일치하지 않습니다.
						</div>

					</div>
			</div>
			<div class="mb-3">
				<label for="inputEmail" class="form-label">이메일</label>
				<div class="input-group">
					<div class="mb-12">
						<input id="inputEmail" type="email" class="form-control" name="email" value="" />
						<button class="btn btn-outline-secondary" type="button" id="checkEmailBtn">중복확인</button>
					</div>

					<div class="d-none form-text text-primary" id="availableEmailMessage">
						<i class="fa-solid fa-check"></i> 사용 가능한 이메일입니다.
					</div>
					<div class="d-none form-text text-danger" id="notAvailableEmailMessage">
						<i class="fa-solid fa-triangle-exclamation"></i> 사용 불가능한 이메일입니다.
					</div>
				</div>
				<div class="mb-3">
					<input id="signupSubmit" type="submit" class="btn btn-primary" value="가입" disabled />
				</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<!-- 부트 스트랩 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!--jquery-->
<script src="/js/member/signup.js"></script>
</body>
</html>