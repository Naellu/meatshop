let cId = $('#cId').val();
const footerToast = document.getElementById('footerToast');
$("#fixedDiv").click(
	function() {
		if (cId === 'anonymousUser') {
			const toastBootstrap = bootstrap.Toast
				.getOrCreateInstance(footerToast);
			$(".toast-body").text("로그인 후 이용해주세요");
			toastBootstrap.show();
		} else {
			$("#chatmodal").css("display", "block");
			createRoom();
		}
	});

$(".close").click(function() {
	$("#chatmodal").css("display", "none");
	$("#customerFrame").empty();
});

function createRoom() {
	var msg = {
		roomName: cId
	};

	$.ajax({
		url: "/chat/customer",
		type: "GET",
		success: function(response) {
			$(".chatcontainer").html(response);

			commonAjax('/chat/createRoom', msg, 'post', function(result) {
				createChatingRoom(result);
			});
		},
		error: function() {
			// Ajax 요청이 실패했을 때의 처리
			window.location.href = "/";
			alert("문제가 발생했습니다. 관리자에게 문의해주세요");
		}
	});
}

function goRoom(number, name) {
	$.ajax({
		url: "/chat/moveChating",
		type: "GET",
		data: {
			roomName: name,
			roomNumber: number
		},
		success: function(response) {
			// Ajax 요청이 성공했을 때의 처리
			// response에는 서버로부터 받은 데이터가 포함된다.
			// jsp파일 링크를 리턴햇기때문에 그게 그대로 리턴된다.
			$(".chatcontainer").html(response);
		},
		error: function() {
			// Ajax 요청이 실패했을 때의 처리
			window.location.href = "/";
			alert("문제가 발생했습니다. 관리자에게 문의해주세요");
		}
	});
}

function createChatingRoom(res) {
	if (res != null) {
		var tag = "";

		var filteredRes = res.filter(function(d) {
			// 필터링 조건을 여기에 작성합니다
			return d.roomName.trim() === cId;
		});

		filteredRes
			.forEach(function(d) {
				var rn = d.roomName.trim();
				var roomNumber = d.roomNumber;

				tag += "<tr>"
					+ "<td class='go'>"
					+ "<button class='btn btn-success' type='button' onclick='goRoom(\""
					+ roomNumber
					+ "\", \""
					+ rn
					+ "\")'> <i class='fa-solid fa-comments'></i> 관리자와채팅하기</button>"
					+ "</td>" + "</tr>";
			});

		$("#roomList").empty().append(tag);
	}
}

function commonAjax(url, parameter, type, calbak, contentType) {
	$.ajax({
		url: url,
		data: parameter,
		type: type,
		contentType: contentType != null ? contentType
			: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(res) {
			calbak(res);
		},
		error: function(err) {
			console.log('error');
			calbak(err);
		}
	});
}

