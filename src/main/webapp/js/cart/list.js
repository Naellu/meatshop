// Select/Deselect all items when the "Select All" checkbox is clicked
$(document).ready(function() {
    $('#checkAll').click(function() {
        $('.item-check').prop('checked', this.checked);
    });

    // If an item checkbox is unchecked, uncheck the "Select All" checkbox too
    $('.item-check').change(function() {
        if ($('.item-check:checked').length === $('.item-check').length) {
            $('#checkAll').prop('checked', true);
        } else {
            $('#checkAll').prop('checked', false);
        }
    });

    // Delete button click event
    $('.btn-delete').click(function() {
        // Delete the item from the cart
        // TODO: Implement the delete functionality
    });
});


$(document).ready(function () {
	$('#orderButton').on('click', function () {
        var selectedCartItems = [];

        $('input.item-check:checked').each(function () {
            var cartItemId = $(this).val();
            var cartItem = {
                id: cartItemId,
                // productName: $('#productName_' + cartItemId).text(),
                memberId: $('#memberId').val(),
                productId: $('#productId_' + cartItemId).val(),
                quantity: $('#quantity_' + cartItemId).text(),
                price: $('#productPrice_' + cartItemId).text()
            };

            selectedCartItems.push(cartItem);
        });

        var jsonData = JSON.stringify(selectedCartItems);

        $.ajax({
            url: '/order/detail',
            type: 'POST',
            data: jsonData,
            contentType: 'application/json',
            success: function (response) {
                // Handle the success response
                console.log(jsonData);
                 window.location.href = "/order/detail";
            },
            error: function (xhr, status, error) {
                // Handle the error
            }
        });
    });
});