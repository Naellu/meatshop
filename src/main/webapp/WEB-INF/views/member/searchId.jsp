<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%><!-- 태그이용을 위한 링크 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
<my:commonFont></my:commonFont>
<title>Insert title here</title>
</head>
<body>



	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">

				<div class="xans-element- xans-member xans-member-findid ">
					<!-- $returnURL = /member/id/find_id_result.html -->
					<div class="findId">
						<h3>
							<img src="http://img.echosting.cafe24.com/design/skin/default_en/member/h3_find_id.gif" alt="Forgot ID">
						</h3>
						<!-- <form method="post" action="searchId"> -->
							<fieldset>
								<legend>아이디 찾기</legend>
								<p class="check displaynone">
									<!-- radio inputs here -->
								</p>
								<p id="name_view" class="name" style="display: none;">
									<strong id="name_lable">Name</strong>
								</p>
								<p id="email_view" class="email" style="">
									<strong>가입 때 사용한 이메일을 입력하세욘</strong> <input id="email_Check" name="email" placeholder="" value="" type="text">
								</p>
								<p class="button">
									<button type="submit" id = "emailButton">
										<img src="http://img.echosting.cafe24.com/design/skin/default_en/member/btn_submit.gif" alt="Submit">
									</button>
								</p>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="container-lg">
			<div class="row justify-content-center">
				<div class="col-12 col-md-8 col-lg-6">
					<label>인증번호확인</label> <input class="form-control mail-check-input proofcode" placeholder="인증번호 2자리를 입력해주세요!" maxlength="6" id="mailconfirm">
				</div>
				<div>
					<input id="signupSubmit" type="submit" class="btn btn-primary" value="인증번호 인증" disabled />
				</div>
			</div>

		</div>


	<div>
	<input id="getId" type="submit" class="btn btn-primary" value="" />

	
	</div>


		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
			<!-- 부트 스트랩 -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
			<!--jquery-->
			<script src="/js/member/searchId.js"></script>
</body>
</html>