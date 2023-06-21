const memberId = $("#memberId").val();
const bucketUrl = $("#bucketUrl").val();
listView(memberId);

function listView(memberId) {
	$.ajax("/wish/view?memberId=" + memberId, {
		method: "get", // 생략 가능
		success: function(productList) {
			$("#viewTable > tbody").empty();
			productList.forEach(function(product, index) {
				let productLink = '';
				if (product.pub === 0 || product.stockQuantity === 0) {
					productLink = '<a class="deleteBtn btn btn-secondary me-3 disabled">상품준비중</a>';
				} else {
					productLink = `<a class="deleteBtn btn btn-secondary me-3" href="/product/info/${product.productId}">상품페이지로</a>`;
				}

				$("#viewTable > tbody").append(`
    <tr>
      <td>${index + 1}</td>
      <td>
      	<img alt="사진준비중" src="${bucketUrl}/product/${product.productId}/main.png" width="100" height="100">
      </td>
      <td>${product.productName}</td>
      <td>${product.categoryName}</td>
      <td>${product.countryOfOrigin}</td>
      <td>${product.stockQuantity}
      	<span class="badge text-bg-danger ${product.stockQuantity === 0 ? '' : 'd-none'}">품절</span>
      </td>
      <td>${product.price}원</td>
      <td>
        ${productLink}
        <button id="deleteBtn${product.productId}" class="btn btn-danger" data-id="${product.productId}">삭제</button>
      </td>
    </tr>`);
				$(`#deleteBtn${product.productId}`).on("click", function() {
					handleDeleteButtonClick(`${product.productId}`);
				});
			});
		}
	});
}

// 클릭 이벤트 처리 함수
function handleDeleteButtonClick(productId) {
	// 삭제 버튼이 클릭되었을 때 실행되는 코드
	const data = {
		"productId": productId,
		"memberId": memberId
	}

	$.ajax("/wish/delete", {
		method: "post",
		contentType: "application/json",
		data: JSON.stringify(data),
		complete: function() {
			listView(memberId);
		}
	});
}


