let checkEmail = true;
let checkNickName = true;
let checkPassword = true;
let checkPhoneNumber = true;

function enableSubmit() {
	if (checkEmail && checkNickName && checkPassword && checkPhoneNumber) {
		$("#modifyButton").removeAttr("disabled");
	} else {
		$("#modifyButton").attr("disabled", "");
	}
}

$("#inputEmail").keyup(function() {
	checkEmail = false;
	$("#availableEmailMessage").addClass("d-none");
	$("#notAvailableEmailMessage").addClass("d-none");
	enableSubmit();
});

$("#inputNickName").keyup(function() {
	checkNickName = false;
	$("#availableNickNameMessage").addClass("d-none");
	$("#notAvailableNickNameMessage").addClass("d-none");
	enableSubmit();
});

$("#inputPhoneNumber").keyup(function() {
	// 전화번호 재입력시 
	checkPhoneNumber = false;
	const pn = $("#inputPhoneNumber").val();
	
	const phoneNumberRegex = /^\d{3}\d{3,4}\d{4}$/;
	$("#phoneNumFailText").removeClass("d-none");
	$("#phoneNumSuccessText").addClass("d-none");
	if (phoneNumberRegex.test(pn)) {
		checkPhoneNumber = true;
		let formattedPhoneNumber = "";

		$("#phoneNumSuccessText").removeClass("d-none");
		$("#phoneNumFailText").addClass("d-none");

		if (pn.length === 10) {
			formattedPhoneNumber = pn.substring(0, 3) + "-" + pn.substring(3, 6) + "-" + pn.substring(6);
		} else if (pn.length === 11) {
			formattedPhoneNumber = pn.substring(0, 3) + "-" + pn.substring(3, 7) + "-" + pn.substring(7);
		}

		$("#phoneNumber").val(formattedPhoneNumber);
	}
	enableSubmit();
});


// 이메일 중복확인 버튼이 클릭되면
$("#checkEmailBtn").click(function() {
	const email = $("#inputEmail").val();
	$.ajax("/member/checkEmail/" + email, {
		success: function(data) {

			if (data.available) {
				$("#availableEmailMessage").removeClass("d-none");
				$("#notAvailableEmailMessage").addClass("d-none");
				checkEmail = true;
			} else {
				$("#availableEmailMessage").addClass("d-none");
				$("#notAvailableEmailMessage").removeClass("d-none");
				checkEmail = false;
			}
		},
		complete: enableSubmit
	});
});


// 닉네임 중복확인버튼 클릭시
$("#checkNickNameBtn").click(function() {
	const nickName = $("#inputNickName").val();

	$.ajax("/member/checkNickName/" + nickName, {
		success: function(data) {
			// `{"available" : true}`
			if (data.available) {
				// 사용가능하다는 메세지 출력
				$("#availableNickNameMessage").removeClass("d-none");
				$("#notAvailableNickNameMessage").addClass("d-none");
				// 중복확인 되었다는 표시 
				checkNickName = true;
			} else {
				// 사용 불가능하다는 메세지 출력
				$("#availableNickNameMessage").addClass("d-none");
				$("#notAvailableNickNameMessage").removeClass("d-none");
				// 중복확인 안되었다는 표시
				checkNickName = false
			}
		},
		complete: enableSubmit
	});
});


$("#inputPassword, #inputPasswordCheck").keyup(function() {
	const pw1 = $("#inputPassword").val();
	const pw2 = $("#inputPasswordCheck").val();

	if (pw1 === pw2) {
		$("#modifyButton").removeClass("disabled");
		$("#passwordSuccessText").removeClass("d-none");
		$("#passwordFailText").addClass("d-none");
		checkPassword = true;
	} else {
		$("#modifyButton").addClass("disabled");
		$("#passwordSuccessText").addClass("d-none");
		$("#passwordFailText").removeClass("d-none");
		checkPassword = false;
	}

	enableSubmit();
});

