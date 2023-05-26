// remove 버튼 클릭시 모달에 쓰일 input element에 inquiryId 삽입
var removeButtons = document.getElementsByName("removeButton");

for (var removeButton of removeButtons) {
	removeButton.addEventListener("click", function() {
		var inquiryId = this.id;
		$("#removeInquiry").val(inquiryId);
	});
}

// 문의 답변 내용, 답변 from, 삭제/수정버튼 출력
function listAnswer(inquiryId) {
	$.ajax("/product/inquiryAnswer/get?inquiryId=" + inquiryId, {
		success: function(productAnswer) {
			const answerTextboxId = "#answerContainer" + inquiryId;
			const answer = productAnswer.answer;

			$(answerTextboxId).empty(); // 답변내용 구역 비우고
			if (answer !== undefined) {
				const answerDiv = `
    				<h5 class="answer-title">관리자 답변</h5>
				    <div class="answer-content"><textarea class="form-control" rows="5">${answer}</textarea></div>
			`;
				$(answerTextboxId).append(answerDiv); // 답변 내용추가
			}

			const editButtons = `
					<button id= answerDeleteBtn${productAnswer.inquiryId}" 
						class="answerDeleteButton btn btn-danger"
						data-bs-toggle="modal"
						data-bs-target="#deleteAnswerConfirmModal"
						data-answer-id="${productAnswer.inquiryId}">
							<i class="fa-regular fa-trash-can id="answecan"></i>
						</button>
					<button
						id="answerUpdateBtn${productAnswer.answer}"
						class="answerUpdateButton btn btn-secondary"
						onclick="location.href='/product/inquiryAnswer/modify/${productAnswer.inquiryId}'">
							<i class="fa-regular fa-pen-to-square"></i>
						</button>
			`;

			$(answerTextboxId).append(editButtons); // 삭제/수정버튼 추가

			$(".answerDeleteButton").click(function() {
				const answerId = $(this).attr("data-answer-id");
				$("#deleteAnswerModalButton").attr("data-answer-id", answerId);
			});
		}

	})

}

// 문의답변 삭제 모달에서 삭제 버튼 클릭시 
$("#deleteAnswerModalButton").click(function() {
	const answerId = $(this).attr("data-answer-id");
	$.ajax("/product/inquiryAnswer/inquiryid/" + answerId, {
		method: "delete",
		complete: function(jqXHR) {
			listAnswer(answerId);
			alert(jqXHR.responseJSON.message);

		}
	})
})


// 문의 답변 JASON
var sendAnswerButtons = document.getElementsByName("sendAnswerButton")
for (var sendAnswerButton of sendAnswerButtons) {
	sendAnswerButton.addEventListener("click", function() {
		const inquiryId = (this.id).substring(13);
		const answerContentId = "#answerTextArea" + inquiryId;
		const answer = $(answerContentId).val();
		const data = { inquiryId, answer };

		$.ajax("/product/inquiryAnswer/add", {
			method: "post",
			contentType: "application/json",
			data: JSON.stringify(data),
			success: function(data) {
				alert(data.message)
				listAnswer(inquiryId);
				$(answerContentId).val("");
			},
			error: function() {
				alert("이미 답변이 완료된 문의입니다.");
				$(answerContentId).val("");
			}

		})
	})
}


/* - 보류, 나중에 쓸 수 도?
var answerButtons = document.getElementsByName("inquiryAnswerButton");
for (var answerButton of answerButtons) {
	answerButton.addEventListener("click", function() {

		var inquiryId = (this.id).substring(12);
		var answerTextId = "#answerText" + inquiryId;
		var answerInsertButtonId = "#answerInsertButton" + inquiryId;

		$(answerTextId).removeClass("d-none");
		$(answerInsertButtonId).removeClass("d-none");
	})
}
*/




