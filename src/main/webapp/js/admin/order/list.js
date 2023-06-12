$(document).ready(function(){
	$(".status-select").each(function() {
		if($(this).find("option:selected").val() === "CANCEL") {
			$(this).prop("disabled", true);
		}
	})
	
	
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
				alert(response);
				
				if(status === "CANCEL") {
					$(this).prop("disabled", true);
				}
				
				location.reload();
			},
			error: function(jqXHR, errorThrown) {
				alert(jqXHR);
				location.reload();
			}
		});
	});
});