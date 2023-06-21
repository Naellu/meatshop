
let globalData;
$('#emailButton').click(function() {
	const email = $("#email").val();
	const fullEmail = email;
	$.ajax("/member/searchId/" + fullEmail, {
		type: "POST",
		data: {
			"email": fullEmail
		},
		success: function() {
			alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인 부탁드립니다.");
			globalData = fullEmail;

		}
	});
});




$(document).ready(function() {
	$('.proofcode').on('input', function() {
		if ($(this).val().length >= 2) {
			$("#signupSubmit").removeAttr("disabled");
		} else {
			$("#signupSubmit").attr("disabled", true);
		}
	});

	$('#signupSubmit').click(function() {
		const userCode = $(".proofcode").val();
		$.ajax({
			type: "POST",
			url: "/member/checkCode/" + userCode,
			data: {
				"userCode": userCode
			},
			success: function(data) {
				if (data) {
					// 맞는 경우 처리 로직 작성
					alert("인증번호가 확인되었습니다.");
					$.ajax({
						type: "POST",
						url: "/member/findId",
						data: {
							"email": globalData
						},
						success: function(data2) {
							$("#getId").val(data2.id);
						}
					})
				} else {
					// 틀린 경우 처리 로직 작성
					alert("인증번호가 일치하지 않습니다.");
				}
			},
		});
	});
});



// 이메일 인증번호 체크 함수
/* function chkEmailConfirm(data,proofcode,  emailconfirmTxt) {
	$('#proofcode').click(function() {
		if (data != proofcode.val()) { //
			
			emailconfirmTxt.html(`
				<div class="form-text text-danger" id="emconfirmchk">
					<i class="fa-solid fa-check"></i>
					인증 번호가 틀립니다
				</div>`)
		} else { // 아니면 중복아님
			signupSubmit = abled;
			emailconfirmTxt.html(`
				<div class="form-text text-primary" id="emconfirmchk">
					<i class="fa-solid fa-check"></i>
					인증 완료 되었습니다
				</div>`)
			
		}
	})
} */