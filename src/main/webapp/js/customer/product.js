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

// 상품문의 탭을 클릭하면 실행하는 script
$("#productInquiry").click(function(){ //상품문의를 누르면
	$("a.nav-link").removeClass("active"); // "nav-link"클래스를 가지오 있는 모든 a엘리멘트의 "active" 클래스를 제거 후
	$(this).addClass("active"); // 현재(상품문의)에만 "active"클래스를 추가한다
	var productId = $(this).data('productid');
})


// 상품문의 탭을 클릭하면 실행하는 script
$("#productReview").click(function(){ //상품문의를 누르면
	$("a.nav-link").removeClass("active"); // "nav-link"클래스를 가지오 있는 모든 a엘리멘트의 "active" 클래스를 제거 후
	$(this).addClass("active"); // 현재(상품문의)에만 "active"클래스를 추가한다
})