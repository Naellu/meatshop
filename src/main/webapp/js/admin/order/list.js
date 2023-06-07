$(document).ready(function(){
	$(".status-select").change(function(){
		var orderId = $(this).data("order-id");
		var status = $(this).val();
		
		var requestURL;
		if(status == "CANCEL" || status === "CANCEL") {
			requestURL = "/admin/order/cancel"
		} else {
			requestURL = "/admin/order/update"
		}
		
		const data = {
			id: orderId,
			status: status
		}

		$.ajax({
			type: "POST",
			url: requestURL,
			contentType:"application/json", 
			data: JSON.stringify(data),
			success: function(response) {
				console.log(response);
				alert(response);
				location.reload();
			},
			error: function(error) {
				console.log(error);
				alert(response);
				location.reload();
			}
		});
	});
});