var removeButtons = document.getElementsByName("removeButton");
var inquiryIdInput = document.querySelector('input[name="inquiryId"]');

for (var i = 0; i < removeButtons.length; i++) {
  removeButtons[i].addEventListener("click", function() {
    var inquiryId = this.id; 
    inquiryIdInput.value = inquiryId; 
  });
}