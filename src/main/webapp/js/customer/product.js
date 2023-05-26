$(document).ready(function() {
	const stockQuantity = $("#stockQuantity").val(); // 재고 수량 설정
	// + 버튼 클릭 이벤트 핸들러
	$(".plus-btn").click(function() {
		const input = $(this).siblings(".number-input");
		const currentValue = parseInt(input.val());
		if (currentValue < stockQuantity) {
			input.val(currentValue + 1);
		}
	});

	// - 버튼 클릭 이벤트 핸들러
	$(".minus-btn").click(function() {
		const input = $(this).siblings(".number-input");
		const currentValue = parseInt(input.val());

		if (currentValue > 1) {
			input.val(currentValue - 1);
		}
	});
});

$("#orderButton").click(function() {
	const count = $("#count").val();
	const productId = $("#productId").val();
	const price = $("#price").val();

	//json형식으로 담기
	const data = {
		quantity: count,
		productId: productId,
		price: price
	};

	$.ajax("/order/detail", {
		method: "post",
		contentType: 'application/json',
		data: JSON.stringify(data),
		success : function(){
			//값을 가지고
			// orderdteatil/ odreid로 이동
			//다른페이지로이동
		}
	});
})

$("#goToCartBtn").click(function() {
	const count = $("#count").val();
	const productId = $("#productId").val();
	const price = $("#price").val();

	//json형식으로 담기
	const data = {
		quantity: count,
		productId: productId,
		price: price
	};

	$.ajax("/cart", {
		method: "post",
		contentType: 'application/json',
		data: JSON.stringify(data),
		success : function(){
			//값을 가지고
			// orderdteatil/ odreid로 이동
			//다른페이지로이동
		}
	});
})