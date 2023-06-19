let customerId = $('#customerId').val();
let roomNumber = $("#roomNumber").val();

if (customerId === 'anonymousUser') {
	customerId = "guest";
}

//웹소켓 객체
let ws;

function wsOpen() {
	try {
		ws = new WebSocket("ws://" + location.host + "/chat/" + roomNumber);
		wsEvt();
	} catch (error) {
		// 웹소켓 연결 실패 처리
		$("#messageTextArea").append(`<p>웹소켓 연결에 오류가 발생했습니다...</p>`);
	}
}

function wsEvt() {
	//소켓이 열리면 초기화 세팅하기
	ws.onopen = function(data) {
		$("#messageTextArea").append(`<p>서버에 연결되었습니다...</p>`);
	}

	ws.onerror = function(event) {
		$("#messageTextArea").append(`<p>오류가 발생했습니다...</p>`);
	};

	ws.onmessage = function(data) {
		let msg = data.data;
		if (msg != null && msg.trim() != '') {
			let serverJson = JSON.parse(msg);
			if (serverJson.type === "getId") {
				let sId = serverJson.sessionId != null ? serverJson.sessionId : "";
				if (sId != '') {
					$("#sessionId").val(sId);
				}
			} else if (serverJson.type === "message") {
				if (serverJson.sessionId === $("#sessionId").val()) {
					$("#messageTextArea").append("<div class='me'>관리자: " + serverJson.msg + "</div>");
				} else {
					$("#messageTextArea").append("<div class='admin'>고객님: " + serverJson.msg + "</div>");
				}
			} else {
				console.warn("unknown type!")
			}
		}
	}

	ws.onclose = function(event) {
	};

	// enter눌리면 send() 실행
	$(document).keypress(function(e) {
		if (e.which == 13) {
			e.preventDefault(); // 엔터 키의 기본 동작 방지
			send();
		}
	});

	$("#sendMessageBtn").click(function() {
		send();
	});
}

function send() {
	let data = {
		type: "message",
		roomNumber: roomNumber,
		sessionId: $("#sessionId").val(),
		userName: customerId,
		msg: $("#textMessage").val()
	}
	ws.send(JSON.stringify(data));
	$('#textMessage').val("");
}

//초기화
wsOpen();