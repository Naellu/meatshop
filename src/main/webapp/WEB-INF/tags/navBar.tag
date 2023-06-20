<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="current"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-expand-lg mb-5" style="min-height: 90px; background-color: rgb(10, 10, 10);">
	<div class="container-lg">
		<a class="navbar-brand me-5" href="/" style="color: white; font-weight: 800; font-size: 30px">
			<span>나이스 투 미트 유</span>
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'list' ? 'active' : '' }" href="/product/list" style="color: white;">상품</a>
				</li>

				<!--
				<sec:authorize access="isAuthenticated()">
				
				</sec:authorize>
				 -->
				 
			 	<li class="nav-item me-3">
					<a class="nav-link ${current eq 'nbList' ? 'active' : '' }" href="/noticeBoard/list" style="color: white;">공지사항</a>
				</li>
				
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'faq' ? 'active' : '' }" href="/faq/list" style="color: white;">고객센터</a>
				</li>
				
			</ul>

			<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
			
                <sec:authorize access="isAnonymous()">
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'signup' ? 'active' : '' }" href="/member/signup" style="color: white;">회원가입</a>
				</li>
				</sec:authorize>

				<sec:authorize access="isAnonymous()">
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'login' ? 'active' : '' }" href="/member/login" style="color: white;">로그인</a>
				</li>
				</sec:authorize>
				
				<!--
				<sec:authorize access="isAuthenticated()">
				
				</sec:authorize>
				 -->
				 
				<sec:authorize access="isAuthenticated()">
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'mypage' ? 'active' : '' }" href="/member/mypage?id=<sec:authentication property="name" />" style="color: white;">마이페이지</a>
				</li>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'cart' ? 'active' : '' }" href="/cart" style="color: white;">장바구니</a>
				</li>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
				<li class="nav-item me-3">
					<a class="nav-link ${current eq 'support' ? 'active' : '' }" href="/admin/main" style="color: white;">관리자</a>
				</li>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
				<li class="nav-item me-3">
					<a class="nav-link" href="/member/logout" style="color: #FF4242;">로그아웃</a>
				</li>
				</sec:authorize>

			</ul>
			
			
			
			<!-- 
			<form action="/list" class="d-flex" role="search">
				<div class="input-group">
					<select class="form-select flex-grow-0" style="width: 100px;" name="type" id="">
						<option value="all">전체</option>
						<option value="title" ${param.type eq 'title' ? 'selected' : '' } >제목</option>
						<option value="body" ${param.type eq 'body' ? 'selected' : '' }>본문</option>
						<option value="writer" ${param.type eq 'writer' ? 'selected' : '' }>작성자</option>
					</select>
					
					<input value="${param.search }" name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</form>
			-->
			
		</div>
	</div>
</nav>

<link rel="stylesheet" href="/css/navbar.css">


