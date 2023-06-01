listView();
const bucketUrl = $("#bucketUrl").val();

$("#product").click(function() {
	window.location.href = "/product/list";
});

function listView() {
	$.ajax("/listView", {
		method: "get",
		success: function(result) {
			const fileCount = result.fileCount;
			const productList = result.productList;

			$(".carousel-inner").empty();
			for (let i = 0; i < fileCount; i++) {
				const carouselHTML = `
					<div class="carousel-item ${i === 0 ? 'active' : ''}">
						<img src="${bucketUrl}/main/carousel/main${i + 1}.jpg" class="d-block w-100" alt="banner1">
					</div>`

				$(".carousel-inner").append(carouselHTML);
			}

			$("#productView").empty();
			productList.forEach(function(product) {
				const productHTML =
					`
				<div class="col mb-5">
					<div class="card h-100">
						<div>
							<img class="card-img-top" src="${bucketUrl}/product/1.png" alt="사진준비중!" />
						</div>
						<div class="card-body p-4">
							<div class="text-center">
								<h5 class="fw-bolder">${product.productName}</h5>
								${product.countryOfOrigin}
								<br />
								${product.price}원
								<br />
								남은수량 : ${product.stockQuantity}
								<span class="badge text-bg-danger ${product.stockQuantity === 0 ? '' : 'd-none'}">품절</span>
							</div>
						</div>
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<button onclick="location.href='/product/info/${product.productId}'" class="btn btn-secondary mt-auto" ${product.stockQuantity === 0 ? 'disabled' : ''}>${product.stockQuantity === 0 ? '상품준비중' : '상품 상세 보기'}</button>
							</div>
						</div>
					</div>
				</div>`
				$("#productView").append(productHTML);
			});

		}
	});
};
