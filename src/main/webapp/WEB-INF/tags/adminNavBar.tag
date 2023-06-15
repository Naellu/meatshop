<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="current"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasAuthority('admin')">
	<nav class="navbar navbar-dark navbar-expand-lg bg-dark mb-5">
		<div class="container-lg">
			<a class="navbar-brand" href="/">
				<span>나이스 투 미트 유</span>
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link" href="/admin/main">메인</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/admin/product/list">상품목록</a>
					</li>

					<li class="nav-item">
						<a class="nav-link" href="/admin/order/list">주문목록</a>
					</li>

					<li class="nav-item">
						<a class="nav-link" href="/question/list">문의목록</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</sec:authorize>


