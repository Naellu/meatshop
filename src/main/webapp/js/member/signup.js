/*	let checkId = false;
	let checkEmail = false;
	let checkPhoneNumber = false;
	let checkPassword = false;
	
	function enableSubmit() {
		if (checkId && checkEmail && checkPhoneNumber && checkPassword) {
			$("#signupSubmit").removeAttr("disabled");
		} else {
			$("#signupSubmit").attr("disabled", "");
		}
	}
*/

/*

아이디, 이름, 이메일,전화번호,비밀번호,주소 입력해야함
아이디,이메일은 중복 불가능해야함

비밀번호 재확인 만들기


*/
let checkId = false;
let checkName = false;
let checkEmail = false;
let checkPhoneNumber = false;
let checkPassword = false;
let checkAddress = false;
let emailConfirmChk = false;

function enableSubmit() {
	if (checkId & checkAddress & checkName & checkPassword & checkPhoneNumber & checkEmail & emailConfirmChk) {
		$("#signupSubmit").removeAttr("disabled");
	} else {
		$("#signupSubmit").attr("disabled", "");
	}
}
//---------------------- 아이디 중복 체크 버튼 관련 스크립트 ----------------------
$("#checkIdBtn").click(function() {
	const userid = $("#inputId").val();
	// 입력한 ID와 ajax 요청 보내서
	$.ajax("/member/checkId/" + userid, {
		success: function(data) {
			// `{"available": true}` 

			if (data.available) {
				// 사용가능하다는 메세지 출력
				$("#availableIdMessage").removeClass("d-none");
				$("#notAvailableIdMessage").addClass("d-none");
				checkId = true;
			} else {
				// 사용가능하지 않다는 메세지 출력
				$("#availableIdMessage").addClass("d-none");
				$("#notAvailableIdMessage").removeClass("d-none");
				checkId = false;
			}
		},
		complete: enableSubmit
	})
});


$("#inputId").keyup(function() {
	// 아이디 입력란에 새로운 입력이 들어 왔을시 
	checkId = false;
	$("#availableIdMessage").addClass("d-none")
	$("#notAvailableIdMessage").addClass("d-none")

	// submit 버튼 비활성화
	enableSubmit();
});
//---------------------- 아이디 중복 확인 관련 스크립트 끝 ----------------------

// --------------------  주소칸 반드시 입력해야하는 스크립트 시작 -------

$("#detailAddress").keyup(function() {
	// 주소 중복확인 다시
	checkAddress = false;
	const ad = $("#address").val();
	if (ad != "") {
		checkAddress = true;
	}
	// submit 버튼 비활성화
	enableSubmit();
});
// --------------------  주소칸 반드시 입력해야하는 스크립트 끝-------

$("#inputName").keyup(function() {
	// 주소 입력란에 새로운 입력이 들어왔을시
	checkName = false;
	const na = $("#inputName").val();
	if (na != "") {
		checkName = true;
	}

	enableSubmit();
});

$("#inputPhoneNumber").keyup(function() {
	// 전화번호 재입력시 
	checkPhoneNumber = false;
	const pn = $("#inputPhoneNumber").val();
	if (pn != "") {
		checkPhoneNumber = true;
	}

	enableSubmit();
});

// 확인할 것
$("#inputPassword, #inputPasswordCheck").keyup(function() {
	// 패스워드에 입력한 값
	const pw1 = $("#inputPassword").val();
	// 패스워드확인에 입력한 값이
	const pw2 = $("#inputPasswordCheck").val();

	if (pw1 === pw2) {
		// 같으면
		// submit 버튼 활성화
		$("#signupSubmit").removeAttr("disabled");
		// 패스워드가 같다는 메세지 출력
		$("#passwordSuccessText").removeClass("d-none");
		$("#passwordFailText").addClass("d-none");

		checkPassword = true;
	} else {
		// 그렇지 않으면
		// submit 버튼 비활성화
		$("#signupSubmit").attr("disabled", "");
		// 패스워드가 다르다는 메세지 출력
		$("#passwordFailText").removeClass("d-none");
		$("#passwordSuccessText").addClass("d-none");

		checkPassword = false;
	}

	enableSubmit();
})

//---------------------- 이메일 중복 확인 관련 스크립트 시작 ----------------------
const mailconfirm = $("#mailconfirm");
const emailconfirmTxt = $("#emailconfirmTxt");

$('#mail-Check-Btn').click(function() {
	const email = $("#userEmail1").val();
	const site = $("#userEmail2").val();
	const fullEmail = email + site;
	$("#emailInput").val(fullEmail);
	// 입력한 Email을 ajax 요청 보내서
	$.ajax("/member/checkEmail/" + fullEmail, {
		success: function(data) {
			// `{"available": true}` 
			if (data.available) {
				// 사용가능하다는 메세지 출력
				$("#availableEmailMessage").removeClass("d-none");
				$("#notAvailableEmailMessage").addClass("d-none");
				checkEmail = true;

				$.ajax("/member/mailCheck", {
					type: "POST",
					data: {
						"email": fullEmail
					},
					success: function(data) {
						alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
						//메일확인하러가기 힘들어서 설정 나중에 삭제필요
						console.log(data);
						chkEmailConfirm(data, mailconfirm, emailconfirmTxt);
					}
				});
			} else {
				// 사용가능하지 않다는 메세지 출력
				$("#availableEmailMessage").addClass("d-none");
				$("#notAvailableEmailMessage").removeClass("d-none");
				checkEmail = false;
			}
		},
	})
});

// 이메일 인증번호 체크 함수
function chkEmailConfirm(data, mailconfirm, emailconfirmTxt) {
	mailconfirm.on("keyup", function() {
		if (data != mailconfirm.val()) { //
			emailConfirmChk = false;
			emailconfirmTxt.html(`
				<div class="form-text text-danger" id="emconfirmchk">
					<i class="fa-solid fa-check"></i>
					인증 번호가 틀립니다
				</div>`)
		} else { // 아니면 중복아님
			emailConfirmChk = true;
			emailconfirmTxt.html(`
				<div class="form-text text-primary" id="emconfirmchk">
					<i class="fa-solid fa-check"></i>
					인증 완료 되었습니다
				</div>`)
			enableSubmit();
		}
	})
}
//---------------------- 이메일 중복 확인 관련 스크립트 끝 ----------------------