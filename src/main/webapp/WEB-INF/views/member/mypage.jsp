<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
<style>
.mypage-container__title {
	font-size: 24px;
	font-weight: bold;
	margin-top: 30px;
}

.meat {
	font-size: 50px;
	font-weight: bold;
}

.mypage__userinfo-box {
	display: flex;
	align-items: center;
	margin-top: 30px;
}

.mypage-userinfo__grade {
	width: 100px;
}

.mypage-userinfo__name {
	font-size: 20px;
	font-weight: bold;
}

.mypage-userinfo__signout, .mypage-userinfo__question {
	margin-left: 10px;
}

.mypage-userinfo__data {
	margin-left: 20px;
}

.mypage-userinfo__split {
	border-top: 1px solid #ccc;
	margin: 10px 0;
}

.mypage-userinfo__data-list {
	margin-bottom: 10px;
}

.mypage-userinfo__data-centered {
	display: flex;
	align-items: center;
}

.mypage-userinfo__data-centered h3 {
	margin-right: 10px;
}

.mypage-tab__container {
	margin-top: 30px;
	background-color: #f8f9fa;
	padding: 10px 0;
}

.mypage-tab__centered {
	display: flex;
	justify-content: center;
}

.mypage-tab__text {
	margin: 0 10px;
	cursor: pointer;
	font-weight: bold;
}

.mypage-tab__active {
	color: #000;
	text-decoration: underline;
}

.mypage__userinfo-box {
	display: flex;
	justify-content: center;
	align-items: center;
}

.mypage-userinfo__grade {
	border: 1px solid gold;
	padding: 20px;
}
</style>
</head>
<body>
	<my:navBar />
	<!--  	<a class="btn btn-secondary" href="/member/info?id=${member.id}">내정보</a>
	<a class="btn btn-secondary" href="/wish/list/${member.id}">찜목록</a>
	<a class="btn btn-secondary" href="/order/list/${member.id}">내 주문목록</a>
-->

	<div id = "maindiv" class = "maindiv">
		<section data-v-c2bdf878="" class="mypage__userinfo-box">
			<img data-v-c2bdf878=""
				src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDIxLjEuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPgo8c3ZnIHZlcnNpb249IjEuMSIgaWQ9IuugiOydtOyWtF8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiCgkgeT0iMHB4IiB2aWV3Qm94PSIwIDAgMTAwLjIgMTU4LjYiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDEwMC4yIDE1OC42OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+CjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+Cgkuc3Qwe2ZpbGw6IzUxN0FGODt9Cgkuc3Qxe2ZpbGw6IzVFOTZGNTt9Cgkuc3Qye2ZpbGw6I0ZGRkZGRjt9Cjwvc3R5bGU+CjxnPgoJCgkJPHJlY3QgeD0iNTguNiIgeT0iMi45IiB0cmFuc2Zvcm09Im1hdHJpeCgwLjkyNDIgMC4zODE4IC0wLjM4MTggMC45MjQyIDIwLjExOTYgLTI1LjA1MzkpIiBjbGFzcz0ic3QwIiB3aWR0aD0iMjkuMyIgaGVpZ2h0PSI3MC41Ii8+CgkKCQk8cmVjdCB4PSIxMi40IiB5PSIyLjkiIHRyYW5zZm9ybT0ibWF0cml4KC0wLjkyNDIgMC4zODE4IC0wLjM4MTggLTAuOTI0MiA2Ni41NzE3IDYzLjEzNTUpIiBjbGFzcz0ic3QxIiB3aWR0aD0iMjkuMyIgaGVpZ2h0PSI3MC41Ii8+Cgk8Zz4KCQk8Y2lyY2xlIGNsYXNzPSJzdDEiIGN4PSI1MC4xIiBjeT0iMTExLjEiIHI9IjQ1LjMiLz4KCQk8cGF0aCBjbGFzcz0ic3QyIiBkPSJNNTAuMSwxNTguNmMtMjYuMiwwLTQ3LjUtMjEuMy00Ny41LTQ3LjVzMjEuMy00Ny41LDQ3LjUtNDcuNXM0Ny41LDIxLjMsNDcuNSw0Ny41Uzc2LjMsMTU4LjYsNTAuMSwxNTguNnoKCQkJIE01MC4xLDY4QzI2LjMsNjgsNyw4Ny4zLDcsMTExLjFzMTkuNCw0My4xLDQzLjEsNDMuMXM0My4xLTE5LjQsNDMuMS00My4xUzczLjksNjgsNTAuMSw2OHoiLz4KCTwvZz4KCTxnPgoJCTxwYXRoIGNsYXNzPSJzdDIiIGQ9Ik0yNC44LDEwNmMwLjIsMCwwLjUsMC4xLDAuNywwLjJjMC4yLDAuMSwwLjQsMC40LDAuNSwwLjdsMS45LDdsMS45LTcuMmMwLjEtMC4zLDAuMi0wLjQsMC40LTAuNQoJCQljMC4yLTAuMSwwLjQtMC4xLDAuNy0wLjFjMC4zLDAsMC40LDAuMiwwLjYsMC40YzAuMSwwLjIsMC4xLDAuNCwwLjEsMC43bC0yLjQsOC44Yy0wLjEsMC4zLTAuMiwwLjYtMC41LDAuN3MtMC40LDAuMi0wLjcsMC4yCgkJCXMtMC41LTAuMS0wLjctMC4ycy0wLjQtMC40LTAuNS0wLjdsLTEuOS03LjNsLTIsNy4zYy0wLjEsMC4zLTAuMiwwLjYtMC41LDAuN2MtMC4yLDAuMS0wLjQsMC4yLTAuNywwLjJjLTAuMiwwLTAuNS0wLjEtMC43LTAuMgoJCQlzLTAuNC0wLjQtMC41LTAuN2wtMi40LTguNmMtMC4xLTAuMy0wLjEtMC41LDAuMS0wLjdjMC4xLTAuMiwwLjMtMC4zLDAuNi0wLjRjMC4yLTAuMSwwLjUtMC4xLDAuNywwczAuNCwwLjMsMC41LDAuNmwxLjgsNy4zCgkJCWwxLjktN2MwLjEtMC4zLDAuMi0wLjYsMC41LTAuN0MyNC4zLDEwNiwyNC42LDEwNiwyNC44LDEwNnoiLz4KCQk8cGF0aCBjbGFzcz0ic3QyIiBkPSJNMzYuMSwxMDguNWMwLjUsMCwxLDAuMSwxLjUsMC4yYzAuNCwwLjIsMC44LDAuNCwxLjEsMC43YzAuMywwLjMsMC42LDAuNywwLjcsMS4xYzAuMiwwLjQsMC4zLDAuOSwwLjMsMS41djAKCQkJYzAsMC41LTAuMSwwLjgtMC4zLDFjLTAuMiwwLjItMC41LDAuMy0xLDAuM2gtNC4ydjBjMCwwLjUsMC4yLDEsMC41LDEuM2MwLjMsMC4zLDAuOCwwLjUsMS41LDAuNWMwLjQsMCwwLjgsMCwxLjEtMC4xCgkJCWMwLjMtMC4xLDAuNi0wLjIsMS0wLjNjMC4yLTAuMSwwLjQtMC4xLDAuNiwwYzAuMiwwLjEsMC4zLDAuMiwwLjQsMC40YzAuMSwwLjIsMC4xLDAuNCwwLjEsMC42YzAsMC4yLTAuMiwwLjMtMC40LDAuNAoJCQljLTAuNCwwLjItMC44LDAuMy0xLjIsMC40Yy0wLjQsMC4xLTEsMC4xLTEuNiwwLjFjLTEuMiwwLTIuMS0wLjMtMi44LTFjLTAuNy0wLjYtMS0xLjYtMS0yLjl2LTAuNGMwLTEuMywwLjMtMi4zLDEtMi45CgkJCUMzNCwxMDguOSwzNC45LDEwOC41LDM2LjEsMTA4LjV6IE0zNiwxMTAuMWMtMC42LDAtMS4xLDAuMi0xLjQsMC41Yy0wLjMsMC4zLTAuNSwwLjctMC41LDEuMnYwLjFoMy43di0wLjFjMC0wLjUtMC4xLTAuOS0wLjQtMS4yCgkJCUMzNy4xLDExMC4yLDM2LjcsMTEwLjEsMzYsMTEwLjF6Ii8+CgkJPHBhdGggY2xhc3M9InN0MiIgZD0iTTQyLjMsMTA1LjZjMC4yLDAsMC41LDAuMSwwLjcsMC4yYzAuMiwwLjEsMC4zLDAuMywwLjMsMC42djkuNWMwLDAuMy0wLjEsMC41LTAuMywwLjYKCQkJYy0wLjIsMC4xLTAuNCwwLjItMC43LDAuMmMtMC4yLDAtMC41LTAuMS0wLjctMC4yYy0wLjItMC4xLTAuMy0wLjMtMC4zLTAuNnYtOS41YzAtMC4zLDAuMS0wLjUsMC4zLTAuNgoJCQlDNDEuOSwxMDUuNyw0Mi4xLDEwNS42LDQyLjMsMTA1LjZ6Ii8+CgkJPHBhdGggY2xhc3M9InN0MiIgZD0iTTQ4LjgsMTA4LjVjMC42LDAsMS4xLDAuMSwxLjUsMC4yYzAuNCwwLjEsMC44LDAuMywxLDAuNWMwLjIsMC4xLDAuMywwLjMsMC4zLDAuNWMwLDAuMiwwLDAuNC0wLjIsMC42CgkJCWMtMC4xLDAuMi0wLjMsMC4zLTAuNSwwLjNjLTAuMiwwLjEtMC40LDAtMC42LTAuMWMtMC41LTAuMy0xLTAuNC0xLjctMC40Yy0wLjYsMC0xLjEsMC4yLTEuNCwwLjZjLTAuMywwLjQtMC41LDAuOS0wLjUsMS43djAuNAoJCQljMCwwLjcsMC4yLDEuMywwLjUsMS43YzAuMywwLjQsMC44LDAuNiwxLjQsMC42YzAuNywwLDEuMy0wLjEsMS44LTAuNGMwLjItMC4xLDAuNC0wLjEsMC42LTAuMWMwLjIsMCwwLjQsMC4yLDAuNSwwLjQKCQkJYzAuMSwwLjIsMC4yLDAuNCwwLjEsMC42YzAsMC4yLTAuMSwwLjQtMC4zLDAuNWMtMC4zLDAuMi0wLjcsMC40LTEuMSwwLjVjLTAuNSwwLjEtMSwwLjItMS41LDAuMmMtMS4yLDAtMi4xLTAuMy0yLjctMQoJCQljLTAuNy0wLjYtMS0xLjYtMS0yLjl2LTAuNGMwLTEuMywwLjMtMi4zLDEtMi45QzQ2LjcsMTA4LjksNDcuNiwxMDguNSw0OC44LDEwOC41eiIvPgoJCTxwYXRoIGNsYXNzPSJzdDIiIGQ9Ik01Ni43LDEwOC41YzEuMiwwLDIuMSwwLjMsMi44LDFjMC43LDAuNywxLDEuNiwxLDIuOXYwLjRjMCwxLjMtMC4zLDIuMy0xLDIuOWMtMC43LDAuNy0xLjYsMS0yLjgsMQoJCQlzLTIuMS0wLjMtMi44LTFjLTAuNy0wLjctMS0xLjYtMS0yLjl2LTAuNGMwLTEuMywwLjMtMi4zLDEtMi45QzU0LjYsMTA4LjksNTUuNSwxMDguNSw1Ni43LDEwOC41eiBNNTYuNywxMTAuMgoJCQljLTAuNywwLTEuMiwwLjItMS41LDAuNWMtMC4zLDAuNC0wLjUsMC45LTAuNSwxLjh2MC4zYzAsMC44LDAuMiwxLjQsMC41LDEuOHMwLjgsMC41LDEuNSwwLjVzMS4yLTAuMiwxLjUtMC41czAuNS0wLjksMC41LTEuOAoJCQl2LTAuM2MwLTAuOC0wLjItMS40LTAuNS0xLjhDNTcuOSwxMTAuNCw1Ny40LDExMC4yLDU2LjcsMTEwLjJ6Ii8+CgkJPHBhdGggY2xhc3M9InN0MiIgZD0iTTY2LjQsMTA4LjVjMC41LDAsMSwwLjEsMS40LDAuM2MwLjQsMC4yLDAuNywwLjUsMC44LDAuOWMwLjMtMC40LDAuNi0wLjcsMS0wLjljMC40LTAuMiwwLjktMC4zLDEuNS0wLjMKCQkJYzAuNCwwLDAuNywwLjEsMS4xLDAuMmMwLjMsMC4xLDAuNiwwLjMsMC45LDAuNmMwLjMsMC4zLDAuNSwwLjYsMC42LDFjMC4yLDAuNCwwLjIsMC44LDAuMiwxLjN2NC4zYzAsMC4zLTAuMSwwLjUtMC4zLDAuNgoJCQljLTAuMiwwLjEtMC40LDAuMi0wLjcsMC4yYy0wLjIsMC0wLjUtMC4xLTAuNi0wLjJjLTAuMi0wLjEtMC4zLTAuMy0wLjMtMC42VjExMmMwLTAuNCwwLTAuNy0wLjEtMC45Yy0wLjEtMC4yLTAuMi0wLjQtMC4zLTAuNgoJCQljLTAuMS0wLjEtMC4zLTAuMi0wLjUtMC4zYy0wLjIsMC0wLjQtMC4xLTAuNi0wLjFjLTAuMiwwLTAuNCwwLTAuNSwwLjFjLTAuMiwwLjEtMC4zLDAuMS0wLjUsMC4zYy0wLjEsMC4xLTAuMywwLjMtMC4zLDAuNQoJCQljLTAuMSwwLjItMC4xLDAuNS0wLjEsMC44djMuOWMwLDAuMy0wLjEsMC41LTAuMywwLjZjLTAuMiwwLjEtMC40LDAuMi0wLjcsMC4yYy0wLjIsMC0wLjUtMC4xLTAuNi0wLjJjLTAuMi0wLjEtMC4zLTAuMy0wLjMtMC42CgkJCVYxMTJjMC0wLjMsMC0wLjYtMC4xLTAuOWMtMC4xLTAuMi0wLjItMC40LTAuMy0wLjVjLTAuMS0wLjEtMC4zLTAuMi0wLjUtMC4zYy0wLjItMC4xLTAuNC0wLjEtMC42LTAuMWMtMC40LDAtMC44LDAuMS0xLjEsMC40CgkJCWMtMC4zLDAuMy0wLjQsMC43LTAuNCwxLjN2NGMwLDAuMy0wLjEsMC41LTAuMywwLjZjLTAuMiwwLjEtMC40LDAuMi0wLjcsMC4yYy0wLjIsMC0wLjUtMC4xLTAuNi0wLjJjLTAuMi0wLjEtMC4zLTAuMy0wLjMtMC42CgkJCXYtNi41YzAtMC4zLDAuMS0wLjQsMC4zLTAuNmMwLjItMC4xLDAuNC0wLjIsMC42LTAuMmMwLjIsMCwwLjQsMC4xLDAuNiwwLjJjMC4yLDAuMSwwLjMsMC4zLDAuMywwLjZ2MC40YzAuMi0wLjQsMC41LTAuNywwLjktMC45CgkJCUM2NS40LDEwOC43LDY1LjgsMTA4LjUsNjYuNCwxMDguNXoiLz4KCQk8cGF0aCBjbGFzcz0ic3QyIiBkPSJNNzkuMywxMDguNWMwLjUsMCwxLDAuMSwxLjUsMC4yYzAuNCwwLjIsMC44LDAuNCwxLjEsMC43YzAuMywwLjMsMC42LDAuNywwLjcsMS4xYzAuMiwwLjQsMC4zLDAuOSwwLjMsMS41djAKCQkJYzAsMC41LTAuMSwwLjgtMC4zLDFjLTAuMiwwLjItMC41LDAuMy0xLDAuM2gtNC4ydjBjMCwwLjUsMC4yLDEsMC41LDEuM2MwLjMsMC4zLDAuOCwwLjUsMS41LDAuNWMwLjQsMCwwLjgsMCwxLjEtMC4xCgkJCWMwLjMtMC4xLDAuNi0wLjIsMS0wLjNjMC4yLTAuMSwwLjQtMC4xLDAuNiwwYzAuMiwwLjEsMC4zLDAuMiwwLjQsMC40YzAuMSwwLjIsMC4xLDAuNCwwLjEsMC42YzAsMC4yLTAuMiwwLjMtMC40LDAuNAoJCQljLTAuNCwwLjItMC44LDAuMy0xLjIsMC40Yy0wLjQsMC4xLTEsMC4xLTEuNiwwLjFjLTEuMiwwLTIuMS0wLjMtMi44LTFjLTAuNy0wLjYtMS0xLjYtMS0yLjl2LTAuNGMwLTEuMywwLjMtMi4zLDEtMi45CgkJCUM3Ny4zLDEwOC45LDc4LjIsMTA4LjUsNzkuMywxMDguNXogTTc5LjMsMTEwLjFjLTAuNiwwLTEuMSwwLjItMS40LDAuNWMtMC4zLDAuMy0wLjUsMC43LTAuNSwxLjJ2MC4xaDMuN3YtMC4xCgkJCWMwLTAuNS0wLjEtMC45LTAuNC0xLjJDODAuNCwxMTAuMiw4MCwxMTAuMSw3OS4zLDExMC4xeiIvPgoJPC9nPgo8L2c+Cjwvc3ZnPgo="
				width="100px" class="mypage-userinfo__grade">
			<div data-v-c2bdf878="">
				<div data-v-c2bdf878="" class="mypage-userinfo__data-top">

					<p data-v-c2bdf878="" class="mypage-userinfo__name">환영합니다, ${member.id } 님</p>
					<br />
					<button type="button" class="btn btn-outline-info">
						<a class="nav-link" href="/member/logout">로그아웃</a>
					</button>
					<button type="button" class="btn btn-outline-info">
						<a href="/question/list">내 문의 내역</a>
					</button>
					<div data-v-c2bdf878="" class="mypage-userinfo__split"></div>

				</div>
				<ul data-v-c2bdf878="" class="mypage-userinfo__data-bottom">
					<li data-v-c2bdf878="" class="mypage-userinfo__data-list"><div data-v-c2bdf878="" class="mypage-userinfo__data-centered">
							<h3 data-v-c2bdf878="">회원등급</h3>
							<p data-v-c2bdf878="">[웰컴]</p>
						</div></li>
					<li data-v-c2bdf878="" class="mypage-userinfo__data-list"><div data-v-c2bdf878="" class="mypage-userinfo__data-centered">
							<h3 data-v-c2bdf878="">적립금</h3>
							<p data-v-c2bdf878="">[0원]</p>
						</div></li>
					<li data-v-c2bdf878="" class="mypage-userinfo__data-list"><div data-v-c2bdf878="" class="mypage-userinfo__data-centered">
							<h3 data-v-c2bdf878="">쿠폰</h3>
							<p data-v-c2bdf878="">[0개]</p>
						</div></li>
					<li data-v-c2bdf878="" class="mypage-userinfo__data-list"><div data-v-c2bdf878="" class="mypage-userinfo__data-centered">
							<h3 data-v-c2bdf878="">구매</h3>
							<p data-v-c2bdf878="">[0건]</p>
						</div></li>
					<li data-v-c2bdf878="" class="mypage-userinfo__data-list"><div data-v-c2bdf878="" class="mypage-userinfo__data-centered">
							<h3 data-v-c2bdf878="">회원번호</h3>
							<p data-v-c2bdf878="">[2306-3629-0523-2008]</p>
						</div></li>
				</ul>
			</div>
		</section>
	</div>

	<div data-v-c2bdf878="" class="mypage-tab__container">
		<div data-v-c2bdf878="" class="mypage-tab__centered">

			<div>
				<button type="button" class="btn btn-primary btn-lg">
					<a class="nav-link" href="/product/list">쇼핑하러가기 &nbsp;<i class="fa-solid fa-play"></i></a>
				</button>
			</div>
			<div data-v-c2bdf878="" class="mypage-tab__text mypage-tab__active ">
				<a class="btn btn-secondary" href="/order/list/${member.id}">내 주문목록</a>
			</div>
			<div data-v-c2bdf878="" class="mypage-tab__text">
				<a class="btn btn-secondary" href="/wish/list/${member.id}">찜목록</a>
			</div>
			<div data-v-c2bdf878="" class="mypage-tab__text">
				<a class="btn btn-secondary" href="/member/info?id=${member.id}">내정보</a>
			</div>
		</div>
	</div>


	<my:footer />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>