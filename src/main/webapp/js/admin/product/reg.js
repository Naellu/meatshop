let productName = false;
let price = false;
let stockQuantity = false;

function enableSubmit() {
	if (productName && price && stockQuantity) {
		$("#addBtn").removeAttr("disabled");
	} else {
		$("#addBtn").attr("disabled", "");
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










