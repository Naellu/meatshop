$(document).ready(function() {
	$("#list1").show();
	$("#list2").hide();
	$("#list3").hide();
	$("#list4").hide();
	$("#list5").hide();
	$("#list6").hide();

	$("#product").click(function() {
		$("#list1").show();
		$("#list2").hide();
		$("#list3").hide();
		$("#list4").hide();
		$("#list5").hide();
		$("#list6").hide();
	});

	$("#order").click(function() {
		$("#list1").hide();
		$("#list2").show();
		$("#list3").hide();
		$("#list4").hide();
		$("#list5").hide();
		$("#list6").hide();
	});

	$("#deliver").click(function() {
		$("#list1").hide();
		$("#list2").hide();
		$("#list3").show();
		$("#list4").hide();
		$("#list5").hide();
		$("#list6").hide();
	});

	$("#cancel").click(function() {
		$("#list1").hide();
		$("#list2").hide();
		$("#list3").hide();
		$("#list4").show();
		$("#list5").hide();
		$("#list6").hide();
	});

	$("#profile").click(function() {
		$("#list1").hide();
		$("#list2").hide();
		$("#list3").hide();
		$("#list4").hide();
		$("#list5").show();
		$("#list6").hide();
	});

	$("#service").click(function() {
		$("#list1").hide();
		$("#list2").hide();
		$("#list3").hide();
		$("#list4").hide();
		$("#list5").hide();
		$("#list6").show();
	});
	// 각 삭제 버튼에 대한 클릭 이벤트 처리
	$("[id^='removeButton-']").on("click", function() {
		var faqId = $(this).attr("id").split("-")[1]; // FAQ 항목의 고유한 id 추출
		$("#confirmDeleteButton").off("click"); // 기존의 클릭 이벤트 제거
		$("#deleteConfirmModal").modal("show"); // 삭제 확인 모달 표시

		// 모달에서 확인 버튼 클릭 시 삭제 작업 수행
		$("#confirmDeleteButton").on("click", function() {
			// 삭제할 FAQ 항목의 form 선택하여 submit
			$("#removeForm").submit();
		});
	});
});
