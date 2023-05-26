/**
 * 
 
$(document).ready(function() {
    // Attach a submit handler to the form
    $('#orderDetailForm').submit(function(event) {
      // Stop form from submitting normally
      event.preventDefault();
      
      // Get form elements values
      var formData = $(this).serializeArray();
      var jsonData = {};
      
      // Convert form data to JSON
      $.each(formData, function() {
        if (jsonData[this.name]) {
          if (!jsonData[this.name].push) {
            jsonData[this.name] = [jsonData[this.name]];
          }
          jsonData[this.name].push(this.value || '');
        } else {
          jsonData[this.name] = this.value || '';
        }
      });
      
      // order/payed로 POST보내서 주문생성
      $.ajax({
        url: "/order/payed",
        type: "POST",
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        success: function(response) {
          console.log(response);
           window.location.href = "/order/success"; // 완료되면 주문완료 화면 보여주기
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log(textStatus, errorThrown);

        }
      });
    });
  });
  */
  
  $("#paymentButton").click(function() {
  	const quantity = $("#quantity").val();
	const productId = $("#productId").val();
	const price = $("#price").val();
	
	const data = {
		quantity: quantity,
		productId: productId,
		price: price
	};
	
	$.ajax({
        url: "/order/payed",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response) {
          console.log(response);
           window.location.href = "/order/success"; // 완료되면 주문완료 화면 보여주기
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log(textStatus, errorThrown);

        }
	  });
	});