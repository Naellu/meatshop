listView();
const bucketUrl = $("#bucketUrl").val();
const memberId = $("#memberId").val();
const toastLiveExample = $("#liveToast");

// jQuery 코드: 각 링크 클릭 시 현재 URL의 파라미터 유지하고 category 파라미터 추가
const currentUrl = window.location.href; // 현재 URL 가져오기

const cateLink = addQueryParam(currentUrl, 'category', 'all');
$('#allLink').attr('href', cateLink);

const beefLink = addQueryParam(currentUrl, 'category', '1');
$('#beefLink').attr('href', beefLink);

const porkLink = addQueryParam(currentUrl, 'category', '2');
$('#porkLink').attr('href', porkLink);

const chickenLink = addQueryParam(currentUrl, 'category', '3');
$('#chickenLink').attr('href', chickenLink);

// jQuery 함수: 현재 URL에 파라미터 추가 및 수정
function addQueryParam(url, paramName, paramValue) {
	const urlObj = new URL(url);
	if (paramValue === 'all') {
		urlObj.searchParams.delete(paramName);
	} else {
		urlObj.searchParams.set(paramName, paramValue);
	}
	urlObj.searchParams.delete('page'); // 페이지 파라미터를 항상 1로 설정
	return urlObj.toString();
}

function listView() {
	// 현재 URL에서 쿼리스트링 추출
	const queryParams = new URLSearchParams(window.location.search);

	// 쿼리스트링에서 변수 값 추출
	const page = queryParams.get('page');
	const categoryId = queryParams.get('category');
	const type = queryParams.get('type');
	const search = queryParams.get('search');

	$.ajax("/product/listView", {
		method: "get",
		data: {
			page: page,
			category: categoryId,
			type: type,
			search: search
		},
		success: function(result) {
			const pageInfo = result.pageInfo;
			const productList = result.productList;
			$("#productView").empty();
			productList.forEach(function(product, index) {
				const starShape = product.liked ? 'fa-solid' : 'fa-regular';
				const productHtml = `
				<div class="col mb-5">
						<div class="card h-100">
							<div>
								<img class="card-img-top" src="${bucketUrl}/product/${product.productId}/main.png" alt="사진준비중!" />
								<i class="${starShape} fa-star fa-2x wish-icon" style="position: absolute; top: 0; right: 0;" data-productid="${product.productId}"></i>
							</div>
							<div class="card-body p-4">
								<div class="text-center">
									<h5 class="fw-bolder">${product.productName}</h5>
									${product.countryOfOrigin}
									<br />
									${product.price}원
									<br />
									남은수량 : ${product.stockQuantity}
									<span class="badge text-bg-warning ${product.stockQuantity <= 10 && product.stockQuantity > 0 ? '' : 'd-none'}">마감임박</span>
									<span class="badge text-bg-danger ${product.stockQuantity === 0 ? '' : 'd-none'}">품절</span>
								</div >
							</div >
			<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
				<div class="text-center">
					<button onclick="location.href='/product/info/${product.productId}'" class="btn btn-secondary mt-auto" ${product.stockQuantity === 0 ? 'disabled' : ''}>상품 상세 보기</button>
			</div>
							</div >
						</div >
					</div >
				`;
				$("#productView").append(productHtml);
			});

			$("#pageUl").empty();
			createPagination(pageInfo);

			$('.wish-icon').click(function() {
				let productId = $(this).data('productid');

				const data = {
					"productId": productId
				};

				const self = $(this);

				$.ajax("/wish/like", {
					method: "post",
					contentType: "application/json",
					data: JSON.stringify(data),
					success: function(response) {
						if (response.like) {
							self.addClass("fa-solid");
							self.removeClass("fa-regular");
						} else {
							self.removeClass("fa-solid");
							self.addClass("fa-regular");
						}
					},
					error: function(response) {
						const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
						$(".toast-body").text(response.responseJSON.message);
						toastBootstrap.show();
					}
				});


			});
		}
	});
}

// '취소' 버튼 클릭 시
$('#cancelSearchButton').click(function() {
	location.href = window.location.pathname;
});

function createPagination(pageInfo) {
	// 현재 URL에서 쿼리스트링 추출
	const queryParams = new URLSearchParams(window.location.search);

	// 쿼리스트링에서 변수 값 추출
	const page = queryParams.get('page');
	const categoryId = queryParams.get('category');
	const type = queryParams.get('type');
	const search = queryParams.get('search');
	if (pageInfo.currentPageNumber !== 1) {
		let pageLink = '/product/list?page=1';
		if (type !== null) {
			pageLink += '&type=' + queryParams.get('type');
		}
		if (search !== null) {
			pageLink += '&search=' + queryParams.get('search');
		}
		if (categoryId !== null) {
			pageLink += '&category=' + queryParams.get('category');
		}
		let prevPageItem = $('<li class="page-item"></li>');
		let prevPageLink = $('<a class="page-link" href="' + pageLink + '"><i class="fa-solid fa-angles-left"></i></a>');
		prevPageItem.append(prevPageLink);
		$("#pageUl").append(prevPageItem);
	}

	// 이전 페이지 (prevPageNumber)
	if (pageInfo.currentPageNumber !== 1) {
		let pageLink = '/product/list?page=' + pageInfo.prevPageNumber;
		if (type !== null) {
			pageLink += '&type=' + queryParams.get('type');
		}
		if (search !== null) {
			pageLink += '&search=' + queryParams.get('search');
		}
		if (categoryId !== null) {
			pageLink += '&category=' + queryParams.get('category');
		}
		let prevPageItem = $('<li class="page-item"></li>');
		let prevPageLink = $('<a class="page-link" href="' + pageLink + '"><i class="fa-solid fa-angle-left"></i></a>');
		prevPageItem.append(prevPageLink);
		$("#pageUl").append(prevPageItem);
	}

	// 페이지 번호
	for (let pageNum = pageInfo.leftPageNumber; pageNum <= pageInfo.rightPageNumber; pageNum++) {
		let pageLink = '/product/list?page=' + pageNum;
		if (type !== null) {
			pageLink += '&type=' + queryParams.get('type');
		}
		if (search !== null) {
			pageLink += '&search=' + queryParams.get('search');
		}
		if (categoryId !== null) {
			pageLink += '&category=' + queryParams.get('category');
		}
		let pageItem = $('<li class="page-item"></li>');
		let pageLinkItem = $('<a class="page-link' + (pageNum === pageInfo.currentPageNumber ? ' active' : '') + '" href="' + pageLink + '">' + pageNum + '</a>');
		pageItem.append(pageLinkItem);
		$("#pageUl").append(pageItem);
	}

	// 다음 페이지 (nextPageNumber)
	if (pageInfo.currentPageNumber !== pageInfo.lastPageNumber) {
		let pageLink = '/product/list?page=' + pageInfo.nextPageNumber;
		if (type !== null) {
			pageLink += '&type=' + queryParams.get('type');
		}
		if (search !== null) {
			pageLink += '&search=' + queryParams.get('search');
		}
		if (categoryId !== null) {
			pageLink += '&category=' + queryParams.get('category');
		}
		let nextPageItem = $('<li class="page-item"></li>');
		let nextPageLink = $('<a class="page-link" href="' + pageLink + '"><i class="fa-solid fa-angle-right"></i></a>');
		nextPageItem.append(nextPageLink);
		$("#pageUl").append(nextPageItem);
	}

	// 마지막 페이지
	if (pageInfo.currentPageNumber !== pageInfo.lastPageNumber) {
		let pageLink = '/product/list?page=' + pageInfo.lastPageNumber;
		if (type !== null) {
			pageLink += '&type=' + queryParams.get('type');
		}
		if (search !== null) {
			pageLink += '&search=' + queryParams.get('search');
		}
		if (categoryId !== null) {
			pageLink += '&category=' + queryParams.get('category');
		}
		let lastPageItem = $('<li class="page-item"></li>');
		let lastPageLink = $('<a class="page-link" href="' + pageLink + '"><i class="fa-solid fa-angles-right"></i></a>');
		lastPageItem.append(lastPageLink);
		$("#pageUl").append(lastPageItem);
	}
}


