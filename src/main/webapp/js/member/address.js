$("#searchAddress").click(function() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.

			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			let addr = ''; // 주소 변수
			let extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져올 수 있다.
			// 도로명 주소 = R 지번주소 = J
			// 사용자가 도로명 주소를 선택했을 경우
			if (data.userSelectedType === 'R') {
				addr = data.roadAddress;
			} else {
				// 사용자가 지번 주소를 선택했을 경우(J)
				// addr = data.jibunAddress;	
				addr = data.roadAddress;
			}

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== ''
				&& /[동|로|가]$/g.test(data.bname)) {
				extraAddr += data.bname;
			}

			// 건물명이 있고 공동주택일 경우 추가한다.
			if (data.buildingName !== ''
				&& data.apartment === 'Y') {
				extraAddr += (extraAddr !== '' ? ', '
					+ data.buildingName
					: data.buildingName);
			}
			// 표시할 참고항목이 있을 경우 괄호까지 추가한 최종 문자열을 만든다.
			if (extraAddr !== '') {
				extraAddr = ' (' + extraAddr + ')';
			}

			$("#postCode").val(data.zonecode);
			$("#inputAddress").val(addr + extraAddr);
			$("#detailAddress").focus();
		}
	}).open();
});

$("#detailAddress").keyup(function() {
	const postCode = $("#postCode").val();
	const addr = $("#inputAddress").val();
	const detailAddr = $("#detailAddress").val();

	const finalAddress = `(${postCode}) ${addr} ${detailAddr}`;
	
	$("#address").val(finalAddress);
});