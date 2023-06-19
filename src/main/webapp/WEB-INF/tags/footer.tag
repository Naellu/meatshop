<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="/css/footer.css">
<div id="myFooter">
	<!-- toast -->
	<div class="toast-container position-fixed top-0 start-50 translate-middle-x p-3">
		<div id="footerToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
			<div class="d-flex">
				<div class="toast-body"></div>
				<button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
			</div>
		</div>
	</div>

	<input type="hidden" id="cId" value="<sec:authentication property="name" />" />
	<div id="fixedDiv" class="fixed-div">
		<i class="fas fa-comments"></i>
	</div>

	<div id="chatmodal" class="modal">
		<div class="modal-content">
			<div class="chatcontainer">
				<div id="roomContainer" class="roomContainer">
					<table id="roomList" class="roomList mx-auto"></table>
				</div>
			</div>
			<iframe id="customerFrame" frameborder="0"></iframe>
			<button class="close btn btn-danger">닫기</button>
		</div>
	</div>

	<footer class="site-footer mt-5">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-6">
					<h6>About</h6>
					<p class="text-justify">
						팀: 김재정, 김하민, 박춘수, 유현우, 정회민
						<br />
						주제 : 고기 쇼핑몰
						<br />
					</p>
				</div>

				<div class="col-xs-6 col-md-3">
					<h6>Categories</h6>
					<ul class="footer-links">
						<li>
							<a href="/product/list">전체상품보기</a>
						</li>
						<li>
							<a href="/product/list?category=1">소고기</a>
						</li>
						<li>
							<a href="/product/list?category=2">돼지고기</a>
						</li>
						<li>
							<a href="/product/list?category=3">닭고기</a>
						</li>
					</ul>
				</div>

				<div class="col-xs-6 col-md-3">
					<h6>Customer</h6>
					<ul class="footer-links">
						<li>
							<a href="/noticeBoard/list">공지사항</a>
						</li>
						<li>
							<a href="/question/list">1대1문의</a>
						</li>
						<li>
							<a href="/faq/list">FAQ</a>
						</li>
						<li>
							<a class="nav-link" href="/member/mypage?id=<sec:authentication property="name" />">마이페이지</a>
						</li>
						<li>
							<a href="/location">찾아오시는길</a>
						</li>

					</ul>
				</div>
			</div>
		</div>
	</footer>
</div>

<script src="/js/footer/footer.js"></script>

