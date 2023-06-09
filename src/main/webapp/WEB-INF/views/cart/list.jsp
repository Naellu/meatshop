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
<link rel="stylesheet" href="/css/cart/list.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
<style>
    #cartLable {
	    text-align: center;
	    font-weight: 800;
    }
    .product-item {
		display: flex;
		align-items: center;
	}
	
	.product-image {
		max-width: 80px;
		max-height: 80px;
		margin-right: 10px;
	}
	
	.product-name {
		margin: 0;
	}
	
	.table td {
	    vertical-align: middle;
	}
	
</style>
</head>

<body>

	<my:navBar></my:navBar>
	
	<h3 id="cartLable">장바구니</h3>
	
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
                    <tbody class="cartitems table-group-divider">
                        <c:forEach var="cartItem" items="${cartItems}">
                            <tr>
                                <td><input type="checkbox" id="" class="item-check cartItemId" value="${cartItem.id}" data-total-price="${cartItem.productPrice * cartItem.quantity}"></td>
                                <td id="productName_${cartItem.id}">
                                	<div class="product-item">
	                                	<img class="card-img-top mb-5 mb-md-0 product-image" src="${bucketUrl}/product/${cartItem.productId }/main.png" alt=""/>
	                                	<p class="product-name">${cartItem.productName}</p>
                                	</div>
                                </td>
                                
			                    <td id="productPrice_${cartItem.id}" data-unit-price="${cartItem.productPrice}">
			                    	<fmt:formatNumber value="${cartItem.productPrice}" maxFractionDigits="0"/>
			                    </td>
			                    
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