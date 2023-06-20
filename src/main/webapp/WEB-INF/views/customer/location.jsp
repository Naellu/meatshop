<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아오시는길</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoMap}"></script>
<my:commonFont></my:commonFont>
</head>
<body>
	<my:navBar />

	<my:header>
	찾아오시는길
	</my:header>
	<section>
		<div class="container-lg mt-3"></div>
		<div class="mx-auto" style="width: 1300px; height: 400px; position: relative;">
			<div id="map" style="width: 1300px; height: 400px;"></div>
			<!-- 지도타입 컨트롤 div 입니다 -->
			<div class="custom_typecontrol" style="position: absolute; top: 10px; right: 10px; z-index: 1;">
				<button id="centerBtn" class="centerBtn btn btn-light" onclick="setMapType('roadmap')">기존위치로</button>
			</div>
		</div>
	</section>

	<div class="container-lg text-center mt-3">
		<table class="table table-bordered border-dark">
			<tr>
				<th scope="row" class="fs-5">주소</th>
				<td colspan="3">03937 서울시 마포구 월드컵로 212(성산동)</td>
			</tr>
			<tr>
				<th class="align-middle fs-5" scope="row" rowspan="2" colspan="1">교통편</th>
				<td>일반버스</td>
				<td>마을버스</td>
				<td>지하철</td>
			</tr>
			<tr>
				<td>271, 710, 7011</td>
				<td>마포 08</td>
				<td>지하철 6호선 마포구청역 1번출구 약200m</td>
			</tr>
		</table>
	</div>

	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/customer/location.js"></script>
</body>
</html>