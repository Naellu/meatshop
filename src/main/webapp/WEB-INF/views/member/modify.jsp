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
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<h1>회원 정보 수정</h1>

				<form id="modifyForm" action="/member/modify" method="post">
					<div class="mb-3">
						<label class="form-label" for="inputId"> 아이디 </label>
						<input id="inputId" class="form-control" type="text" name="id" value="${member.id }" readonly />
					</div>
					<div class="mb-3">
						<label class="form-label" for="inputPassword"> New Password </label>
						<input id="inputPassword" class="form-control" type="text" name="password" value="" />
					</div>

					<div class="mb-3">
						<label class="form-label" for="inputEmail"> 이메일 </label>
						<input id="inputEmail" class="form-control" type="email" name="email" value="${member.email }" />
					</div>
						
						<div class="mb-3">
						<label class="form-label" for="inputAdress"> 주소 </label>
						<input id="inputAdress" class="form-control" type="text" name="address" value="${member.address }" />
					</div>
						<div class="mb-3">
						<label class="form-label" for="inputEmail"> 번호수정 </label>
						<input id="inputPhoneNumber" class="form-control" type="tel" name="phoneNumber" value="${member.phoneNumber }" required/>
					</div>
		

					<button id="modifyButton" type="button" data-bs-toggle="modal" data-bs-target="#confirmModal" class="btn btn-primary">수정</button>
				</form>

			</div>
		</div>
	</div>
	
	<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">수정 확인</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label for="inputOldPassword" class="form-label">이전 암호</label>
					<input form="modifyForm" id="inputOldPassword" class="form-control" type="text" name="oldPassword" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					<button type="submit" form="modifyForm" class="btn btn-primary">확인</button>
				</div>
			</div>
		</div>
	</div>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script><!-- 부트 스트랩 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script><!--jquery-->
</body>
</html>