// 리뷰 수정
var modifyReviews = document.getElementsByName("modifyReview");
for (var modifyReview of modifyReviews) {
	modifyReview.addEventListener("click", function() {
		const reviewId = $(this).data('reviewId');
		const data = {
			reviewId: reviewId
		}
		$.ajax("/product/review/modify", {
			data: data,
			contentType: 'application/json',
			success: function(reviewModifyPage) {
				$("#content").empty();
				$("#content").html(reviewModifyPage);

				}
		})
	})
}



// 리뷰 삭제
var removeReviews = document.getElementsByName("removeReview");
for (var removeReview of removeReviews) {
	removeReview.addEventListener("click", function() {
		var reviewId = $(this).data('reviewId')
		$("#removeReviewById").attr("value", reviewId);

	})
}


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
			$("#content").empty(); // 컨텐트 박스 비우고
			$("#content").html(reviewAddPage); // reviewAddform 출력

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