

// 문의 답변 내용, 답변 from, 삭제/수정버튼 출력
function listAnswer(inquiryId) {
	$.ajax("/product/answer/get?inquiryId=" + inquiryId, {
		success: function(productAnswer) {
			const answerTextboxId = "#answerContainer" + inquiryId;
			const answer = productAnswer.answer;

			$(answerTextboxId).empty(); // 답변내용 구역 비우고
			if (answer !== undefined) {
				const answerDiv = `
    				<h5 class="answer-title">관리자 답변</h5>
				    <div id="answerContent" class="answer-content"><textarea class="form-control" rows="5">${answer}</textarea></div>
			`;
				$(answerTextboxId).append(answerDiv); // 답변 내용추가
			}

			const editButtons = `
					<button id= "answerDeleteBtn" 
						class="answerDeleteButton btn btn-danger"
						data-bs-toggle="modal"
						data-bs-target="#deleteAnswerConfirmModal"
						data-inquiry-id="${productAnswer.inquiryId}">
							<i class="fa-regular fa-trash-can id="answecan"></i>
						</button>
					<button
						id="answerUpdateBtn"
						class="answerUpdateButton btn btn-secondary"
						data-inquiry-id="${productAnswer.inquiryId}"
						data-answer="${productAnswer.answer}"
						>
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

// 문의 답변 수정 
// 문서 로드 후 실행
$(document).ready(function() {
	// 문의답변 수정버튼 클릭시 (이벤트 위임 사용)
	$(document).on("click", "#answerUpdateBtn", function() {
		const inquiryId = $(this).data('inquiryId');
		const data = {
			inquiryId: inquiryId
		};


		$.ajax("/product/answer/modify", {
			data: data,
			contentType: 'application/json',
			success: function(answerModifyPage) {
				const answerContentId = "#answerContainer" + inquiryId;
				$("#answerOfInquiry").empty();
				$(answerContentId).html(answerModifyPage);
				
				 $("#modifyAnswerBtn").click(function() {

					const inquiryId = $("#inquiryId").val();
					const answer = $("#bodyTextarea").val();
					
					console.log(inquiryId)
					console.log(answer)
					
					const modifyData = {
						inquiryId: inquiryId,
						answer: answer
					}

					$.ajax("/product/answer/modify", {
						method: "post",
						contentType: 'application/json',
						data: JSON.stringify(modifyData),
						success: function(result) {
							alert(result.message)
						},
						complete: function() {
							listAnswer(inquiryId);
						}
					})
				})

			}
		});
	});
});



// 문의답변 삭제 모달에서 삭제 버튼 클릭시 
$("#deleteAnswerModalButton").click(function() {
	const inquiryid = $("#answerDeleteBtn").data("inquiryId");
	$.ajax("/product/answer/delete/" + inquiryid, {
		method: "delete",
		complete: function(jqXHR) {
			listAnswer(inquiryid);
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

		$.ajax("/product/answer/add", {
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


// 문의 등록
$("#addInquiry").click(function() {
	const productId = $(this).data('productId');
	const customerId = $(this).data('customerId');
	const data = {
		productId: productId,
		customerId: customerId
	};
	$.ajax("/product/inquiry/add", {
		data: data,
		contentType: 'application/json',
		success: function(inquiryAddPage) {
			$("#content").empty(); // 컨텐트 박스 비우고
			$("#content").html(inquiryAddPage); // inquiryAddform 출력

			$("#addInquiry").click(function() { //문의하기 누르면
				const productId = $("#productId").val();
				const customerId = $("#customerId").val();
				const customerName = $("#customerName").val();
				const inquiryTitle = $("#inquiryTitle").val();
				const inquiryText = $("#bodyTextarea").val();


				const data = {
					productId: productId,
					customerId: customerId,
					customerName: customerName,
					inquiryTitle: inquiryTitle,
					inquiryText: inquiryText
				}

				$.ajax("/product/inquiry/add", {
					method: "post",
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function(data) {
						alert(data.message)
						loadInquiryPage(productId, customerName)

					},
					error: function() {
						alert("문의를 등록하지 못했습니다.")
						loadInquiryPage(productId, customerName)

					}
				})

			})

		}
	})

})

// 문의 수정
$("#modifyInquiry").click(function() {
	const inquiryId = $(this).data('inquiryId')
	console.log(inquiryId)

	const data = {
		inquiryId: inquiryId
	}

	$.ajax("/product/inquiry/modify", {
		data: data,
		contentType: 'application/json',
		success: function(inquiryModifyPage) {
			$("#content").html(inquiryModifyPage);


			$("#modifyInquiryBtn").click(function() {
				const inquiryId = $("#inquiryId").val();
				const productId = $("#productId").val();
				const customerId = $("#customerId").val();
				const inquiryTitle = $("#inquiryTitle").val();
				const inquiryText = $("#bodyTextarea").val();
				const modifyData = {
					inquiryId: inquiryId,
					inquiryTitle: inquiryTitle,
					inquiryText: inquiryText
				}

				$.ajax("/product/inquiry/modify", {
					method: "post",
					contentType: 'application/json',
					data: JSON.stringify(modifyData),
					success: function(result) {
						alert(result.message)
					},
					complete: function() {
						loadInquiryPage(productId, customerId)
					}
				})
			})

		}
	})
})

// 문의 삭제
$("#removeInquiryBtn").click(function() {
	const inquiryId = $("#removeInquiry").data("inquiryId");
	const productId = $("#removeInquiry").data("productId");
	const customerId = $("#removeInquiry").data("customerId");
	const deleteData = {
		inquiryId: inquiryId
	}
	$.ajax("/product/inquiry/delete", {
		method: "post",
		contentType: 'application/json',
		data: JSON.stringify(deleteData),
		success: function(result) {
			alert(result.message)
		},
		complete: function() {
			loadInquiryPage(productId, customerId)
		}
	})

})

// 상품문의 출력 함수
function loadInquiryPage(productId, customerId) {

	$("#content").empty();

	const data = {
		productId: productId,
		customerId: customerId,
		page: 1
	};
	$.ajax("/product/inquiry/list", { //data의 값을 파라미터 형식으로 전달
		data: data,
		success: function(inquiryPage) {
			$("#content").html(inquiryPage); // jsp 페이지를 HTML 형태로 삽입


		}
	});

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




