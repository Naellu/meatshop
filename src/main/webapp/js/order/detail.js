	
$(document).ready(function() {
	
	var data = [];
		
	$("table tbody tr").each(function() {
		var row = $(this);
		var quantity = row.find("#quantity").val();
		var productId = row.find("#productId").val();
        var price = row.find("#price").val();
        var fromCart = row.find("#fromCart").val();
        
        data.push({
			quantity: quantity,
			productId: productId,
			price: price,
			fromCart: fromCart
		});
	});
	console.log(data);
	
	// 결제없이 단순 주문
	$("#simpleOrderButton").click(function() {
		$.ajax({
	        url: "/order/payed",
	        type: "POST",
	        data: JSON.stringify(data),
	        contentType: "application/json",
	        success: function(response) {
	          console.log("success: " + response);
	          window.location.href="/order/success";
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	          console.log("error: " + textStatus, errorThrown);
	        }
	  	});
	});	
	
	// 결제 버튼 누를 시 결제진행
	$("#paymentButton").click(function() {
		$.ajax({
	        url: "/order/payed",
	        type: "POST",
	        data: JSON.stringify(data),
	        contentType: "application/json",
	        success: function(response) {
	          console.log("success: " + response);
	          // 주문이 생성되고 나면 주문데이터를 결제 함수인 requestPay()로 전달
	          requestPay(response);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	          console.log("error: " + textStatus, errorThrown);
	        }
	  	});
	});	
	
	// 총 주문금액 계산
	var totalPrice = 0;
    
    $('tr').each(function() {
		var price = parseFloat($(this).find('input[id=price]').val());
		var quantity = parseFloat($(this).find('input[id=quantity]').val());
		
		if(!isNaN(price) && !isNaN(quantity)) {
			totalPrice += price * quantity;
		}
	});
    
    $('#totalPrice').text(totalPrice.toLocaleString());
    $('#totalPrice').val(totalPrice);
    
    
})


$(document).ready(function() {
    // 아임포트 - 가맹점 식별코드
    var IMP = window.IMP;
	IMP.init("imp51807046");
})
	
	var today = new Date();   
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
	

	// 아임포트 - 결제 요청
	function requestPay(orderData) {
		console.log(orderData)
		
		let productNameList = orderData.productName;
		let displayName = "";
		
		if(productNameList.length == 1) {
			displayName = productNameList[0];
		} else if (productNameList.length > 1) {
			displayName = productNameList[0] + " 외 " + (productNameList.length - 1) + "건";
		}
		
    IMP.request_pay({
        pg : 'html5_inicis.INIpayTest', // 고정
        pay_method : 'card', // 고정
        merchant_uid: orderData.orderId, // (가맹점)주문번호 - order_id
        name : displayName, // 1건보다 많을 시 대표 상품 외 n건 - productName
        amount : 100, // 테스트 하는 동안에는 100원으로 설정, total_price
        buyer_email : orderData.email,
        buyer_name : orderData.memberName,
        buyer_tel : orderData.phoneNumber,
        buyer_addr : orderData.address,
	    }, function (rsp) { // callback
	    console.log(rsp);
	        if (rsp.success) {
	      	// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
    	  	// jQuery로 HTTP 요청
		      $.ajax({
		        url: "/payment/complete", // 결제 검증 요청(어떤 데이터를 보내 검증해야하는지?)
		        method: "POST",
		        contentType: "application/json",
		        data: JSON.stringify({
	          		imp_uid: rsp.imp_uid,            // 결제 고유번호
	          		merchant_uid: rsp.merchant_uid   // 주문번호
		        })
		      }).done(function(data) {
		          // 가맹점 서버 결제 API 성공시 로직
				  // window.location.href="/order/success";
				  
				  // 반환된 결제 데이터를 가지고 가격검증
				  verifyAmount(data);
		      })
		    } else {
		      alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
		    }
	    });
	}
	
	function verifyAmount(paymentData) {
		console.log(paymentData);
		$.ajax({
			url: "/payment/verify",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify(paymentData),
			success: function(response) {
				console.log(response);
				// 주문상태를 주문완료에서 결제완료로 바꿔야함
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("error: " + textStatus, errorThrown);
			}
		})
	}
	
