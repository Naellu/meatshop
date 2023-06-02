
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