var likeIcons = document.getElementsByName('likeIcons');
for (var likeIcon of likeIcons) {
	likeIcon.addEventListener("click", function() {
		
		var reviewId = $(this).data('reviewId');
		var customerId = $(this).data('customerId');
		
		var likeId = "#likeIcon" + reviewId;
		var likeNumber = "#likeNumber" + reviewId
		const data = {reviewId, customerId};
		

		$.ajax("/product/review/like", {
			method: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			success: function(data) {
				
				if (data.like) {
					$(likeId).html(`<i class="fa-solid fa-thumbs-up fa-beat" style="color: #166fe3;"></i>`);
				} else {
					$(likeId).html(`<i class="fa-regular fa-thumbs-up" style="color: #166fe3;"></i>`);
				}

				$(likeNumber).text(data.count);
			},
			error: function(jqXHR) {
				alert(jqXHR.responseJSON.message);
			}

		})


	})

}