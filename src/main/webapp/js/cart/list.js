// Select/Deselect all items when the "Select All" checkbox is clicked
$(document).ready(function() {
    $('#checkAll').click(function() {
        $('.item-check').prop('checked', this.checked);
        updateTotalPrice();
    });

    // If an item checkbox is unchecked, uncheck the "Select All" checkbox too
    $('.item-check').change(function() {
        if ($('.item-check:checked').length === $('.item-check').length) {
            $('#checkAll').prop('checked', true);
        } else {
            $('#checkAll').prop('checked', false);
        }
        updateTotalPrice();
    });
    
    // 체크박스 클릭 시 총 주문가격 계산
    function updateTotalPrice() {
		var total = 0;
	
		$(".item-check:checked").each(function() {
			total += Number($(this).data("price"));
		})
		
		$("#totalPrice").text(total + "원");
	}

    // 장바구니에서 상품 삭제버튼 누를 시
    $('.btn-delete').click(function() {
        
        // 해당 상품 정보를 컨트롤러에 전달
        const cartItemId = $(this).val(); // 클릭 시 버튼에 할당된 id 가져오기
        
        const data = {
			id: cartItemId,
            memberId: $('#memberId').val(),
            productId: $('#productId_' + cartItemId).val(),
            quantity: $('#quantity_' + cartItemId).text(),
            price: $('#productPrice_' + cartItemId).text(),
		};
        
        $.ajax({
			url: '/cart/delete',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(response) {
				// alert(response);
				window.location.replace("/cart");
				updateTotalPrice();
			},
            error: function (xhr, status, error) {
				console.log(error);
            }
		})
    });
    
    // 장바구니 항목 체크시 가격 갱신 
    $(".item-check").change(function() {
		updateTotalPrice();
	})
	
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
                price: $('#productPrice_' + cartItemId).text(),
                fromCart: true
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
             	window.location.href = "/order/detail";
            },
            error: function (xhr, status, error) {
                // Handle the error
            }
        });
    });
    
});




