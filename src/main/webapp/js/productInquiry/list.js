// remove 버튼 클릭시 모달에 쓰일 input element에 inquiryId 삽입
var removeButtons = document.getElementsByName("removeButton");

for (var removeButton of removeButtons) {
	removeButton.addEventListener("click", function() {
		var inquiryId = this.id;
		$("#removeInquiry").val(inquiryId);
	});
}


function listAnswer(inquiryId){
	$.ajax("/productanswer/get?inquiryId="+inquiryId,{
		success:function(answers){
			const answerTextboxId ="#answerContainer" + inquiryId;
			$(answerTextboxId).empty();
			for (const answer of answers){
				const answerDiv = $("<div>").text(answer);  // answer 값을 가지는 <div> 엘리먼트 생성
                $(answerTextboxId).append(answerDiv);  // 생성한 <div> 엘리먼트를 answerTextboxId를 가진 요소 안에 추가
			}
		}
		
	})
	
}


// 문의 답변 JASON
var sendAnswerButtons = document.getElementsByName("sendAnswerButton")
for (var sendAnswerButton of sendAnswerButtons){
	sendAnswerButton.addEventListener("click",function(){
		const inquiryId = (this.id).substring(13);
		const answerContentId = "#answerTextArea"+inquiryId;
		const answer = $(answerContentId).val();
		const data = {inquiryId, answer};
		
		$.ajax("/productanswer/add",{
			method:"post",
			contentType:"application/json",
			data: JSON.stringify(data),
			success: function(data){
				alert(data.message)
				listAnswer(inquiryId);
				$(answerContentId).val("");
			},
			error: function(){
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




