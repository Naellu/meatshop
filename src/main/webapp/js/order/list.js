
$(document).ready(function() {

	// 주문 취소	
	$(".cancelOrderButton").click(function() {
		var orderId = $(this).data("order-id");
		
		const data = {
			id: orderId
		}
		
		$.ajax({
			url: "/order/cancel",
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(response) {
				alert(response);
				location.reload();
			},
			error: function() {
				
			}
		}) 
	})
})

// 리뷰 작성
var addReviewButtons = document.getElementsByName('addReviewButtons');
for (var addReviewButton of addReviewButtons){
	addReviewButton.addEventListener("click",function(){
		var customerId = $(this).data('customerId');
		var productName = $(this).data('productName');
		var productId = productName.substr(2);
		
		var link = '/product/review/add?customerId=' + customerId + '&productId=' + productId;
		
		location.href = link;
		
		
	})
}