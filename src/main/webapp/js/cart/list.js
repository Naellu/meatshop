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