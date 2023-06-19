<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet" href="/css/footer.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<style>
.modal-content {
	width: 60%; /* 원하는 가로 크기로 조정 */
}
</style>
<input type="hidden" id="cId" value="<sec:authentication property="name" />" />

<div id="fixedDiv" class="fixed-div">
	<i class="fas fa-comments"></i>
</div>

<div id="chatmodal" class="modal">
	<div class="modal-content">
		<span class="close">&times;</span>
		<div class="chatcontainer">
			<div id="roomContainer" class="roomContainer">
				<table id="roomList" class="roomList"></table>
			</div>
			<div>
				<table class="inputTable">
					<tr>
						<th>
							<button type="button" id="createRoom">관리자와 채팅하기</button>
						</th>
					</tr>
				</table>
			</div>
		</div>
		<iframe id="customerFrame" frameborder="0"></iframe>
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

<script type="text/javascript">
	$(document).ready(function() {
		$("#fixedDiv").click(function() {
			$("#chatmodal").css("display", "block");
		});

		$(".close").click(function() {
			$("#chatmodal").css("display", "none");
			$("#customerFrame").empty();
		});
	});

	let cId = $('#cId').val();

	if (cId === 'anonymousUser') {
		cId = "guest";
	}

	window.onload = function() {
		getRoom();
		createRoom();
	}

	function getRoom() {
		commonAjax('/chat/getRoom', "", 'post', function(result) {
			createChatingRoom(result);
		});
	}

	function createRoom() {
		$("#createRoom").click(function() {
			var msg = {
				roomName : cId
			};

			commonAjax('/chat/createRoom', msg, 'post', function(result) {
				createChatingRoom(result);
			});
		});
	}

	function goRoom(number, name) {
		$.ajax({
			url : "/chat/moveChating",
			type : "GET",
			data : {
				roomName : name,
				roomNumber : number
			},
			success : function(response) {
				// Ajax 요청이 성공했을 때의 처리
				// response에는 서버로부터 받은 데이터가 포함됩니다.
				$(".chatcontainer").html(response);
				// 모달 창에 받은 데이터를 표시하는 로직을 작성합니다.
				// 예를 들어, 모달 내부의 특정 요소에 데이터를 추가하거나 HTML을 업데이트하는 등의 작업을 수행할 수 있습니다.
			},
			error : function(xhr, status, error) {
				// Ajax 요청이 실패했을 때의 처리
				// 오류 메시지를 표시하거나 오류 처리 로직을 작성할 수 있습니다.
			}
		});
	}

	function createChatingRoom(res) {
		if (res != null) {
			var tag = "<tr><th class='num'>순서</th><th class='room'>방 이름</th><th class='go'></th></tr>";
			var existingRNs = []; // 중복된 rn을 체크하기 위한 배열

			var filteredRes = res.filter(function(d) {
				// 필터링 조건을 여기에 작성합니다
				return d.roomName.trim() === cId;
			});

			filteredRes.forEach(function(d, idx) {
				var rn = d.roomName.trim();
				var roomNumber = d.roomNumber;

				// 중복 체크
				if (existingRNs.includes(rn)) {
					return; // 중복된 경우 다음 반복으로 넘어감
				}
				existingRNs.push(rn); // 중복되지 않은 경우 배열에 추가

				tag += "<tr>" + "<td class='num'>" + (idx + 1) + "</td>"
						+ "<td class='room'>" + rn + "</td>"
						+ "<td class='go'>"
						+ "<button type='button' onclick='goRoom(\""
						+ roomNumber + "\", \"" + rn + "\")'>참여</button>"
						+ "</td>" + "</tr>";
			});

			$("#roomList").empty().append(tag);
		}
	}

	function commonAjax(url, parameter, type, calbak, contentType) {
		$.ajax({
			url : url,
			data : parameter,
			type : type,
			contentType : contentType != null ? contentType
					: 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(res) {
				calbak(res);
			},
			error : function(err) {
				console.log('error');
				calbak(err);
			}
		});
	}
</script>

