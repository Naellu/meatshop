
// Attach a click event handler to the button
$('#submitButton').click(function() {
	const email = $("#email").val();
	const birthday = $("#birthday").val();
	$.ajax({
		type: "POST",
		url: "/member/searchPassword",
		data: {
			"email": email,
			"birthday": birthday
		}
		, success: function(data) {
			if (data === "false") {
				// 틀린 경우 처리 로직 작성
				alert("이메일과 생일을 다시 확인해주세요");
				
			} else {
				// 입력한 정보가 일치할경우
				alert("이메일로 새로운 비밀번호가 전송되었습니다");
			}
		},
	});
})
	   
