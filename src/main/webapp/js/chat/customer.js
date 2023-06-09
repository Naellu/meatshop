let customerId = $('#customerId').val();

if (customerId === 'anonymousUser') {
	customerId = "guest";
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
			roomName: customerId
		};

		commonAjax('/chat/createRoom', msg, 'post', function(result) {
			createChatingRoom(result);
		});
	});
}

function goRoom(number, name) {
	location.href = "/chat/moveChating?roomName=" + name + "&" + "roomNumber=" + number;
}


function createChatingRoom(res) {
	if (res != null) {
		var tag = "<tr><th class='num'>순서</th><th class='room'>방 이름</th><th class='go'></th></tr>";
		var existingRNs = []; // 중복된 rn을 체크하기 위한 배열

		var filteredRes = res.filter(function(d) {
			// 필터링 조건을 여기에 작성합니다
			return d.roomName.trim() === customerId;
		});

		filteredRes.forEach(function(d, idx) {
			var rn = d.roomName.trim();
			var roomNumber = d.roomNumber;

			// 중복 체크
			if (existingRNs.includes(rn)) {
				return; // 중복된 경우 다음 반복으로 넘어감
			}
			existingRNs.push(rn); // 중복되지 않은 경우 배열에 추가

			tag += "<tr>"
				+ "<td class='num'>"
				+ (idx + 1)
				+ "</td>"
				+ "<td class='room'>"
				+ rn
				+ "</td>"
				+ "<td class='go'>"
				+ "<button type='button' onclick='goRoom(\"" + roomNumber + "\", \"" + rn + "\")'>참여</button>"
				+ "</td>"
				+ "</tr>";
		});

		$("#roomList").empty().append(tag);
	}
}

function commonAjax(url, parameter, type, calbak, contentType) {
	$.ajax({
		url: url,
		data: parameter,
		type: type,
		contentType: contentType != null ? contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(res) {
			calbak(res);
		},
		error: function(err) {
			console.log('error');
			calbak(err);
		}
	});
}