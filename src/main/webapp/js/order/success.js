
$('#continueShoppingButton').click(function() {
	window.location.href = '/product/list';
});

$('#orderListButton').click(function() {
	console.log(memberId);
	window.location.href = '/order/list/' + $('#memberId').val();
});