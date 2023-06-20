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


<style>
  .containers {
    max-width: 500px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid gold;
  }

  .form-title {
    text-align: center;
    margin-bottom: 20px;
  }

  .form-group {
    margin-bottom: 20px;
  }

  .form-label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
  }

  .form-control {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  .btn-primary {
    display: block;
    width: 100%;
    padding: 10px;
    text-align: center;
    background-color: gold;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  .btn-primary:hover {
    background-color: goldenrod;
  }
</style>
<!-- 부트 스트랩 -->
<my:commonFont></my:commonFont>
<title>Insert title here</title>
</head>
<body>

	<my:navBar></my:navBar>
<div class="containers">
  <div class="form-title">
    <h3>Forgot ID</h3>
  </div>
  <form>
    <fieldset>
      <legend>아이디 찾기</legend>
      <div class="form-group">
        <label class="form-label" for="email">가입 때 사용한 이메일을 입력하세요</label>
        <input id="email" class="form-control" type="email" placeholder="Email" required>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary">Submit</button>
      </div>
    </fieldset>
  </form>
</div>

<div class="containers">
  <div class="row justify-content-center">
    <div class="col-12 col-md-8 col-lg-6">
      <div class="form-group">
        <label class="form-label">인증번호 확인</label>
        <input id="mailconfirm" class="form-control mail-check-input proofcode" type="text" placeholder="인증번호 2자리를 입력하세요!" maxlength="6">
      </div>
      <div class="form-group">
        <input id="signupSubmit" type="submit" class="btn btn-primary" value="인증번호 인증" disabled>
      </div>
<div>
  <input id="getId" type="submit" class="btn btn-primary" value="아이디가 나타납니다">
</div>
    </div>
  </div>
</div>

<my:footer />

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
			<!-- 부트 스트랩 -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
			<!--jquery-->
			<script src="/js/member/searchId.js"></script>
</body>
</html>