listView();
const bucketUrl = $("#bucketUrl").val();

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
		success(result) {
			const pageInfo = result.pageInfo;
			const productList = result.productList;
			$("#productView").empty();
			productList.forEach(function(product, index) {
				const productHtml = `
				<div class="col mb-5">
						<div class="card h-100">
							<div>
								<img class="card-img-top" src="${bucketUrl}/product/1.png" alt="사진준비중!" />
								<i class="fa-regular fa-star fa-2x" style="position: absolute; top: 0; right: 0;"></i>
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
		}
	});
}

