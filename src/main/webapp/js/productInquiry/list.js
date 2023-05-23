var removeButtons = document.getElementsByName("removeButton");
var inquiryIdInput = document.querySelector('input[name="inquiryId"]');

for (var removeButton of removeButtons){
	removeButton.addEventListener("click", function() {
		var inquiryId = this.id; 
    inquiryIdInput.value = inquiryId; 
  });
}