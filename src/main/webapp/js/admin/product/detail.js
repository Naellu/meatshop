$('#notificationBtn').click(function() {
	const productId = $("#productId").val();
	$.ajax("/admin/product/notify", {
		method: "post",
		data: {
			"productId": productId
		}
	});
});