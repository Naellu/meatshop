	
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
		
	$("#paymentButton").click(function() {
		
		
		$.ajax({
	        url: "/order/payed",
	        type: "POST",
	        data: JSON.stringify(data),
	        contentType: "application/json",
	        success: function(response) {
	          console.log(response);
	          //window.location.href = "/order/success"; // 완료되면 주문완료 화면 보여주기
	          
	          // 주문이 생성되고 나면 주문데이터를 결제 함수인 requestPay()로 전달
	          requestPay(response); 
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	          console.log(textStatus, errorThrown);
	
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
    // 아임포트
    var IMP = window.IMP;
	IMP.init("imp51807046");
})
	
	var today = new Date();   
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
	

	// 아임포트
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
        amount : 100, // 가격은 totalPrice를 사용,$("#totalPrice").val() - totalPrice
        buyer_email : orderData.email, // member_email
        buyer_name : orderData.memberName, // member_name
        buyer_tel : orderData.phoneNumber, // member_phone_number
        buyer_addr : orderData.address, // member_address
        buyer_postcode : '123-456' // 없으니 아무거나
	    }, function (rsp) { // callback
	    console.log(rsp);
	        if (rsp.success) {
	      	// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
    	  	// jQuery로 HTTP 요청
    	  	/*
		      $.ajax({
		        url: "{서버의 결제 정보를 받는 가맹점 endpoint}", 
		        method: "POST",
		        headers: { "Content-Type": "application/json" },
		        data: {
		          imp_uid: rsp.imp_uid,            // 결제 고유번호
		          merchant_uid: rsp.merchant_uid   // 주문번호
		        }
		      }).done(function (data) {
				  console.log(data);
				  console.log(orderData);
		        // 가맹점 서버 결제 API 성공시 로직
		        // 성공화면 보여주기 order/success
		      })
		      */
			 console.log(rsp);
			 window.location.href="order/success";
		    } else {
		      alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
		    }
	    });
	}
	
	/*
	// 아임포트
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
        merchant_uid: "meat" + makeMerchantUid, // (가맹점)주문번호 - order_id
        name : '당근 10kg', // 1건보다 많을 시 대표 상품 외 n건 - productName
        amount : $("#totalPrice").val(), // 가격은 totalPrice를 사용 - totalPrice
        buyer_email : 'Iamport@chai.finance', // member_email
        buyer_name : '아임포트 기술지원팀', // member_name
        buyer_tel : '010-1234-5678', // member_phone_number
        buyer_addr : '서울특별시 강남구 삼성동', // member_address
        buyer_postcode : '123-456' // 없으니 아무거나
	    }, function (rsp) { // callback
	    console.log(rsp);
	        if (rsp.success) {
	      	// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
    	  	// jQuery로 HTTP 요청
		      $.ajax({
		        url: "{서버의 결제 정보를 받는 가맹점 endpoint}", 
		        method: "POST",
		        headers: { "Content-Type": "application/json" },
		        data: {
		          imp_uid: rsp.imp_uid,            // 결제 고유번호
		          merchant_uid: rsp.merchant_uid   // 주문번호
		        }
		      }).done(function (data) {
				  console.log(data);
				  console.log(orderData);
		        // 가맹점 서버 결제 API 성공시 로직
		        // 성공화면 보여주기 order/success
		      })
		    } else {
		      alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
		    }
	    });
	}
	*/
	
