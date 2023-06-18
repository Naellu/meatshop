
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
				cancelPay(data);
				location.reload();
			},
			error: function() {
				
			}
		}) 
	})
})

// 결제 취소
function cancelPay(data) {
    $.ajax({
      // 예: http://www.myservice.com/payments/cancel
      url: "/payment/cancel", 
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        merchantUid: data.id, // 예: ORD20180131-0000011
      }),
      success: function() {
		  alert("테스트 결제취소 성공");
	  },
	  error: function(jqXHR, errorThrown) {
		  console.log(jqXHR, errorThrown);
	  }
    });
  }


// 리뷰 작성
var addReviewButtons = document.getElementsByName('addReviewButtons');
for (var addReviewButton of addReviewButtons){
	addReviewButton.addEventListener("click",function(){
		var customerId = $(this).data('customerId');
		var productId = $(this).data('productId');
		
		var link = '/product/review/add?customerId=' + customerId + '&productId=' + productId;
		
		location.href = link;
		
		
	})
}