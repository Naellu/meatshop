<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="current"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasAuthority('admin')">
	<nav class="navbar navbar-expand-lg mb-5" style="min-height: 90px; background-color: rgb(10, 10, 10);">
		<div class="container-lg">
			<a class="navbar-brand" href="/" style="color: white; font-weight: 800;">
				<span>나이스 투 미트 유</span>
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link" href="/admin/main" style="color: white;">메인</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/admin/product/list" style="color: white;">상품목록</a>
					</li>

					<li class="nav-item">
						<a class="nav-link" href="/admin/order/list" style="color: white;">주문목록</a>
					</li>

					<li class="nav-item">
						<a class="nav-link" href="/question/list" style="color: white;">문의목록</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/chat/admin/list" style="color: white;">채팅목록</a>
					</li>

					<li class="nav-item">
						<a class="nav-link " href="/member/list" style="color: white;">회원목록</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</sec:authorize>


