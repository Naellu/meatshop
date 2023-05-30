var openIds = []; // openIds 배열 선언
var ids = []; // ids 배열 선언
const toastLiveExample = document.getElementById('liveToast')

$('#pubBtn').click(function() {
	openIds = []; // openIds 배열 초기화
	ids = []; // ids 배열 초기화

	// 각 체크된 체크박스의 값들을 openIds 배열에 담기
	$('input[name="openIds"]:checked').each(function() {
		openIds.push($(this).val());
	});

	// 각 상품의 productId 값을 ids 배열에 담기
	$('input[name="openIds"]').each(function() {
		ids.push($(this).val());
	});

	//json형식으로 담기
	const data = {
		openIds: openIds,
		ids: ids
	};

	$.ajax("/admin/product/pub", {
		method: "post",
		contentType: 'application/json',
		data: JSON.stringify(data),
		complete: function(jqXHR) {
			const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
			$(".toast-body").text(jqXHR.responseJSON.message);
			toastBootstrap.show();
		}
	});
})

//체크박스 전체 누르기
$("#allCheck").change(function() {
	var isChecked = $(this).is(":checked");

	//openIds에 true면 checked추가 아니면 없애기
	$("input[name='openIds']").prop("checked", isChecked);
});

// 체크박스 해제
$("input[name='openIds']").change(function() {
	var totalOpenIds = $("input[name='openIds']").length;
	var checkedOpenIds = $("input[name='openIds']:checked").length;

	//전체 박스개수와 체크된박스수를 비교 allCheck에 true면 checked추가 아니면 없애기
	$("#allCheck").prop("checked", totalOpenIds === checkedOpenIds);
});
