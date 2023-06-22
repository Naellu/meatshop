// 체크박스 전부선택
$(document).ready(function() {
    $('#checkAll').click(function() {
        $('.item-check').prop('checked', this.checked);
        updateTotalPrice();
    });

    // 몇몇개만 선택했을 경우 전부선택 시 나머지 체크 안된 항목들도 선택
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
			total += Number($(this).data("total-price"));
		})
		
		if(total != 0) {
			$("#totalPrice").text("/ 총 " + total.toLocaleString() + "원");
		} else {
			$("#totalPrice").text(null);
		}
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
            price: $('#productPrice_' + cartItemId).data('unit-price'),
		};
        
        $.ajax({
			url: '/cart/delete',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(response) {
				alert(response);
				//window.location.replace("/cart");
				//location.reload();
			  	$('#productName_' + cartItemId).closest('tr').remove();
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
	
	// 장바구니에서 주문 시
	$('#orderButton').on('click', function () {
        var selectedCartItems = [];
		
        $('input.item-check:checked').each(function () {
            var cartItemId = $(this).val();
            var cartItem = {
                id: cartItemId,
                // productName: $('#productName_' + cartItemId).text(),
                memberId: $('#memberId').val(),
                productId: $('#productId_' + cartItemId).val(),
                quantity: $('#quantity_' + cartItemId).val(),
                price: $('#productPrice_' + cartItemId).data('unit-price'),
                fromCart: true
            };
            selectedCartItems.push(cartItem);
        });
       	console.log(selectedCartItems);

		if(selectedCartItems.length === 0) {
			alert("상품을 선택해주세요");
			return;
		}
		
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
    
    // 수량 증가 버튼
     $('.btn-quantity-increase').click(function() {
        var cartItemId = $(this).find('.increaseInput').val();
        var quantityInput = $('#quantity_' + cartItemId);
        var newQuantity = Number(quantityInput.val()) + 1;
        quantityInput.val(newQuantity);
        updateQuantity(cartItemId, newQuantity);
    });
    
    // 수량 감소 버튼
     $('.btn-quantity-decrease').click(function() {
        var cartItemId = $(this).find('.decreaseInput').val();
        var quantityInput = $('#quantity_' + cartItemId);
        var newQuantity = Number(quantityInput.val()) - 1;
        if (newQuantity >= 1) {
            quantityInput.val(newQuantity);
            updateQuantity(cartItemId, newQuantity);
        }
    });

    
    // 수량 업데이트 요청
    function updateQuantity(cartItemId, newQuantity) {
		
		const data = {
			id: cartItemId,
			quantity: newQuantity
		};
		
		$.ajax({
			url: 'cart/update',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(response) {
				// 장바구니 항목의 가격 가져오기
	            var unitPrice = $('#productPrice_' + cartItemId).data('unit-price');
	
	            // 장바구니 항목에 대한 새로운 총 가격 계산 
	            var newTotalPrice = unitPrice * newQuantity;
	            
	            // 해당 확인란의 data-total-price 속성을 이 항목에 대한 새로운 총 가격으로 업데이트
	            $('.cartItemId[value=' + cartItemId + ']').data('total-price', newTotalPrice);
	
	            // 가격 갱신
	            updateTotalPrice();
	            
	            // 주문 버튼 클릭 시 전송되는 상품 가격도 업데이트
            	$('.item-check[value=' + cartItemId + ']').data('total-price', newTotalPrice);
			},
			error: function(xhr, status, error) {
				console.log(error);
			}
		})
	}
    
});




