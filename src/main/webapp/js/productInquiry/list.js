// remove 버튼 클릭시 모달에 쓰일 input element에 inquiryId 삽입
var removeButtons = document.getElementsByName("removeButton");
var inquiryIdInput = document.querySelector('input[name="inquiryId"]');

for (var removeButton of removeButtons){
	removeButton.addEventListener("click", function() {
		var inquiryId = this.id; 
    	inquiryIdInput.value = inquiryId; 
  });
}

var answerButtons = document.getElementsByName("answerButton");
for (var answerButton of answerButtons){
	answerButton.addEventListener("click",function(){
		var inquiryId = (this.id).substring(6);
		
	})
	
}
