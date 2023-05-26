let productName = true;
let price = true;
let stockQuantity = true;

function enableSubmit() {
	if (productName && price && stockQuantity) {
		$("#updateBtn").removeAttr("disabled");
	} else {
		$("#updateBtn").attr("disabled", "");
	}
}

$("#productNameInput").keyup(function() {
	productName = false;
	const productNameInput = $("#productNameInput").val();
	if (productNameInput != '' && productNameInput != null) {
		productName = true;
	}
	enableSubmit()
});

$("#stockQuantityInput").keyup(function() {
	stockQuantity = false;
	const stockQuantityInput = $("#stockQuantityInput").val();
	if (stockQuantityInput != '' && stockQuantityInput != null) {
		stockQuantity = true;
	}
	enableSubmit()
});

$("#priceInput").keyup(function() {
	price = false;
	const priceInput = $("#priceInput").val();
	if (priceInput != '' && priceInput != null) {
		price = true;
	}
	enableSubmit()
});










