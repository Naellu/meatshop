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
