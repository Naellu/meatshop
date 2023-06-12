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
				    <div id="answerContent" class="answer-content"><textarea readonly="readonly" class="form-control" rows="5">${answer}</textarea></div>
			`;
				$(answerTextboxId).append(answerDiv); // 답변 내용추가
			}

			const editButtons = `
					<button 
						name= "answerDeleteBtn" 
						class="answerDeleteButton btn btn-danger"
						data-bs-toggle="modal"
						data-bs-target="#deleteAnswerConfirmModal"
						data-inquiry-id="${inquiryId}"
						>
							<i class="fa-regular fa-trash-can id="answecan"></i>
					</button>
					<button
						name="answerUpdateBtn"
						class="answerUpdateButton btn btn-secondary"
						data-inquiry-id="${inquiryId}"
						data-answer="${productAnswer.answer}"
						>
							<i class="fa-regular fa-pen-to-square"></i>
						</button>
			`;

			$(answerTextboxId).append(editButtons); // 삭제/수정버튼 추가

			$(".answerDeleteButton").click(function() {
				var inquiryId = $(this).attr("data-inquiry-id");
				$("#deleteAnswerModalButton").attr("data-inquiry-id", inquiryId);
			});
			
			$(".answerUpdateButton").click(function() {
				var inquiryId = $(this).attr("data-inquiry-id");
				var answer = $(this).attr("data-answer");


				const data = {
					inquiryId: inquiryId
				};

				$.ajax("/product/inquiryAnswer/modify", {
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

							$.ajax("/product/inquiryAnswer/modify", {
								method: "post",
								contentType: 'application/json',
								data: JSON.stringify(modifyData),
								success: function(result) {
									alert(result.message)
								},
								complete: function() {
									window.location.reload()
									// window.location.href = '/success-page';


								}
							})
						})

					}
				})
			})
		}

	})

}

// 문의 답변 수정 
// 문서 로드 후 실행

/*var answerUpdateBtns = document.getElementsByName("answerUpdateBtn");
for (var answerUpdateBtn of answerUpdateBtns) {
    answerUpdateBtn.addEventListener("click", function() {
        var inquiryId = this.dataset.inquiryId;
        var customerId = this.dataset.customerId;
        console.log("Clicked on answer update button. Inquiry ID: " + inquiryId + ", Customer ID: " + customerId);
        
        // 여기에 추가 동작을 수행하는 코드를 작성하세요.
        // 예를 들면 AJAX 요청을 보내거나 다른 함수를 호출할 수 있습니다.
    });
}
*/
/*const inquiryId = this.getAttribute('data-inquiry-id');
		const data = {
			inquiryId: inquiryId
		};

		$.ajax("/product/inquiryAnswer/modify", {
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

					$.ajax("/product/inquiryAnswer/modify", {
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
		});*/


// 문의답변 삭제 모달에서 삭제 버튼 클릭시 
$("#deleteAnswerModalButton").click(function() {
	var inquiryid = $(this).data("inquiryId");
	
	$.ajax("/product/inquiryAnswer/delete/" + inquiryid, {
		method: "delete",
		complete: function(jqXHR) {
			alert(jqXHR.responseJSON.message);
			window.location.reload()
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
				window.location.reload()
			},
			error: function() {
				alert("이미 답변이 완료된 문의입니다.");
				window.location.reload()
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
	if(customerId != 'anonymousUser'){
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
							loadInquiryPage(productId, customerId)
	
						},
						error: function() {
							alert("문의를 등록하지 못했습니다.")
							loadInquiryPage(productId, customerId)
	
						}
					})
	
				})
	
			}
		})
	} else{
		alert("로그인 후 이용할 수 있습니다.")
	}

})

// 문의 수정
var modifyInquirys = document.getElementsByName("modifyInquiry");
for (var modifyInquiry of modifyInquirys) {
	modifyInquiry.addEventListener("click", function() {
		const inquiryId = $(this).data('inquiryId')

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

}

// 문의 삭제
var removeInquirys = document.getElementsByName("removeInquiry");
for (var removeInquiry of removeInquirys) {
	removeInquiry.addEventListener("click", function() {
		$("#removeInquiryBtn").data("inquiryId", $(this).data("inquiryId"));
		$("#removeInquiryBtn").data("productId", $(this).data("productId"));
		$("#removeInquiryBtn").data("customerId", $(this).data("customerId"));


		$("#removeInquiryBtn").click(function() {
			var removeInquiryId = $(this).data("inquiryId");
			var productId = $(this).data("productId");
			var customerId = $(this).data("customerId");

			var deleteData = {
				inquiryId: removeInquiryId
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
	})
}



// 페이지버튼 클릭시 페이지이동 내용 ajax 출력
var pageButtons = document.getElementsByName("inquiryPageBtn");
for (var pageButton of pageButtons) {
	pageButton.addEventListener("click", function() {
		var productId = $(this).data('productId');
		var customerId = $(this).data('customerId');
		var page = $(this).data('page');
		var data = {
			productId: productId,
			customerId: customerId,
			page: page
		}

		$.ajax("/product/inquiry/list", {
			data: data,
			success: function(inquiryPage) {
				$("#content").empty();
				$("#content").html(inquiryPage)
			}
		})

	})
}