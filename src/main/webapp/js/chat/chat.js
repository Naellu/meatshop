{
	let customerId = $('#customerId').val();
	let roomNumber = $("#roomNumber").val();

	//웹소켓 객체
	let ws;

	function wsOpen() {
		try {
			ws = new WebSocket("ws://" + location.host + "/chat/" + roomNumber);

			const connectionTime = 3 * 60 * 1000;

			if (ws !== null) {
				// 일정 시간이 지난 후에 연결을 닫음
				setTimeout(function() {
					if (ws.readyState === WebSocket.OPEN) {
						disconnect();
					}
				}, connectionTime);
			}

			wsEvt();
		} catch (error) {
			// 웹소켓 연결 실패 처리
			$("#messageTextArea").append(`<p>웹소켓 연결에 오류가 발생했습니다...</p>`);
		}
	}

	function wsEvt() {
		//소켓이 열리면 초기화 세팅하기
		ws.onopen = function(data) {
			console.log("웹소켓연결됨!!");
			$("#messageTextArea").append(`<p>서버에 연결되었습니다... </br>
		채팅에 응답이 없을시 1대1문의를 이용해주세요...!</p>`);
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
						$("#messageTextArea").append("<div class='me'>" + customerId + "님: " + serverJson.msg + "</div>");
					} else {
						$("#messageTextArea").append("<div class='admin'>관리자: " + serverJson.msg + "</div>");
					}
				} else {
					console.warn("unknown type!")
				}
			}
		}

		ws.onclose = function(event) {
			$("#messageTextArea").append(`<p>채팅 연결이 해제 되었습니다.</p>`);
			console.log("연결해제됨!!!");
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

		//웹소켓 닫는버튼
		$("#chatCloseBtn").click(function() {
			disconnect();
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

	function disconnect() {
		if (ws !== null) {
			let data = {
				type: "close",
				roomNumber: roomNumber,
				sessionId: $("#sessionId").val(),
				userName: customerId,
				msg: $("#textMessage").val()
			}
			//닫았다는 메시지 보내기
			ws.send(JSON.stringify(data));

			//닫기
			ws.close();
			ws = null;

			$.ajax("/chat/deleteRoom", {
				method: "post",
				data: {
					roomNumber: roomNumber
				}
			});
		}
	}
	//초기화
	wsOpen();
}
