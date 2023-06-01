<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet" href="/css/footer.css">

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
				<h6>고객</h6>
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
						<a href="#">마이페이지</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</footer>