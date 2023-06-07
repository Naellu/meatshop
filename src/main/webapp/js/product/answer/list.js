
// 리뷰 등록
$("#addReview").click(function() {
	const productId = $(this).data('productId');
	const customerId = $(this).data('customerId');
	const data = {
		productId: productId,
		customerId: customerId
	};
	$.ajax("/product/review/add", {
		data: data,
		contentType: 'application/json',
		success: function(reviewAddPage) {
			$("#reviewContent").empty(); // 컨텐트 박스 비우고
			$("#reviewContent").html(reviewAddPage); // reviewAddform 출력

			$("#addReview").click(function() { //문의하기 누르면
				const productId = $("#productId").val();
				const customerId = $("#customerId").val();
				const rating = $("#rating").val();
				const content = $("#content").val();


				const data = {
					productId: productId,
					customerId: customerId,
					rating: rating,
					content: content
				}

				$.ajax("/product/review/add", {
					method: "post",
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function(data) {
						alert(data.message)
						loadReviewPage(productId, customerId)

					},
					error: function() {
						alert("문의를 등록하지 못했습니다.")
						loadReviewPage(productId, customerId)

					}
				})

			})

		}
	})

})




// 페이지버튼 클릭시 페이지이동 내용 ajax 출력
var pageButtons = document.getElementsByName("reviewPageBtn");
for (var pageButton of pageButtons) {
	pageButton.addEventListener("click", function() {
		var productId = $(this).data('productId');
		var customerId = $(this).data('customerId');
		var page = $(this).data('page');
		var data = {
			productId: productId,
			customerId: customerId,
			page: page
		}

		$.ajax("/product/review/list", {
			data: data,
			success: function(answerPage) {
				$("#content").empty();
				$("#content").html(answerPage)
			}
		})

	})
}