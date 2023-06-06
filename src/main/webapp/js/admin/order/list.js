$(document).ready(function(){
	$(".status-select").change(function(){
		var orderId = $(this).data("order-id");
		var status = $(this).val();
		
		const data = {
			id: orderId,
			status: status
		}

		$.ajax({
			type: "POST",
			url: "/admin/order/update",
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