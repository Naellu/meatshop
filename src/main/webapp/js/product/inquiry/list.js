// 문의 답변 내용, 답변 from, 삭제/수정버튼 출력
function listAnswer(inquiryId) {
	var adminButton = "#adminButton" + inquiryId;
	$(adminButton).removeClass("d-none");
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
		
			$(".answerDeleteButton").click(function() {
				var inquiryId = $(this).attr("data-inquiry-id");
				$("#deleteAnswerModalButton").attr("data-inquiry-id", inquiryId);
			});
			
			$(".answerUpdateButton").click(function() {
				var inquiryId = $(this).attr("data-inquiry-id");


				const data = {
					inquiryId: inquiryId
				};

				$.ajax("/product/inquiryAnswer/modify", {
					data: data,
					contentType: 'application/json',
					success: function(answerModifyPage) {
						$("#answerOfInquiry").empty();
						var viewModifyPage = "#answerContainer" + inquiryId;
						$(viewModifyPage).html(answerModifyPage);
						$(adminButton).addClass("d-none");

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
					const inquiryTitle = $("#inquiryTitle").val();
					const inquiryText = $("#bodyTextarea").val();
					let disclosure;
					if ($("#disclosure").is(":checked")) {
						disclosure = false;
					} else{
						disclosure = true;
					}
	
	
					const data = {
						productId: productId,
						customerId: customerId,
						inquiryTitle: inquiryTitle,
						inquiryText: inquiryText,
						disclosure: disclosure
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
		
		console.log(data)

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
					let disclosure;
					if ($("#disclosure").is(":checked")) {
						disclosure = false;
					} else{
						disclosure = true;
					}
					
					const modifyData = {
						inquiryId: inquiryId,
						inquiryTitle: inquiryTitle,
						inquiryText: inquiryText,
						disclosure:disclosure
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