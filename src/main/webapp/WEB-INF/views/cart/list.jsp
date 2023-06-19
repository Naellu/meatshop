<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>내 장바구니</title>
<style>
	#buttonDiv {
		display: flex;
		justify-content: center; 
		align-items: center; 
		height: 10vh;
	}
	.table th:nth-child(2) {
   	 	width: 40%;
	}
	
	.table th:nth-child(3),
	.table th:nth-child(4) {
	    width: 20%;
	}
	
	.table td {
		padding-top: 30px;
	}
	
	.quantity-input {
	    width: 40px;
	    border: none;
	    text-align: center;
	}
	
	.quantity-input::-webkit-inner-spin-button,
	.quantity-input::-webkit-outer-spin-button {
	    -webkit-appearance: none;
	    margin: 0;
	}
	
	.quantity-input[type="number"] {
	    -moz-appearance: textfield;
	}
	
	
	.quantity-container {
	    display: flex;
	    border: 1px solid #dcdcdc;
	    align-items: center;
	    padding: 0;
	    box-sizing: border-box; /* 추가 */
	    max-width: 100px;
	}
	
	.custom-counter-clickarea {
	    display: inline-block;
	    width: 30px;
	    height: 30px;
	    background-color: #ffffff;
	    text-align: center;
	    line-height: 30px;
	    cursor: pointer;
	    box-sizing: border-box; /* 추가 */
	    margin: 0; /* 추가 */
	    padding: 0; /* 추가 */
	}
	
	.cartitems > tr {
		margin-top: 10px;
	}

	
	
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

	<my:navBar></my:navBar>
	
	<h3 style="text-align: center;">장바구니</h3>
	
	<br>
	
	<div class="container-lg">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8">
                <table class="table">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="checkAll"></th>
                            <th>상품명</th>
                            <th>상품가격</th>
                            <th>구매수량</th>
                        </tr>
                    </thead>
                    <tbody class="cartitems">
                        <c:forEach var="cartItem" items="${cartItems}">
                            <tr>
                                <td><input type="checkbox" id="" class="item-check cartItemId" value="${cartItem.id}" data-total-price="${cartItem.productPrice * cartItem.quantity}"></td>
                                <td id="productName_${cartItem.id}">${cartItem.productName}</td>
                                
			                    <td id="productPrice_${cartItem.id}" data-unit-price="${cartItem.productPrice}">${cartItem.productPrice}</td>
			                    
			                    <td>
				                    <div class="quantity-container">
		                                <div class="custom-counter-clickarea btn-quantity-decrease">
		                                	<input type="hidden" class="decreaseInput" value="${cartItem.id}"/>
									        <i class="fas fa-minus"></i>
									    </div>
									    <input type="number" id="quantity_${cartItem.id}" class="quantity-input" value="${cartItem.quantity}" min="1" readonly>
									    <div class="custom-counter-clickarea btn-quantity-increase">
									    	<input type="hidden" class="increaseInput" value="${cartItem.id}"/>
									        <i class="fas fa-plus"></i>
									    </div>
								    </div>
			                    </td>
			                    
                                <td>
	                                <button type="button" id="deleteButton" class="btn btn-outline-danger btn-delete" value="${cartItem.id}">
	                                	<i class="fa-solid fa-xmark"></i>
	                                </button>
                                </td>
                            </tr>
			                    <input type="hidden" id="productId_${cartItem.id}" value="${cartItem.productId }"/>
                        </c:forEach>
			                    <input type="hidden" name="memberId" id="memberId" value="${memberId }"/>
                    </tbody>
                </table>
                <div id="buttonDiv">
		            <button id="orderButton" class="btn btn-outline-dark">주문하기 <span id="totalPrice"></span></button>
                </div>
            </div>
        </div>
    </div>
	
	<my:footer></my:footer>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/cart/list.js"></script>

</body>
</html>