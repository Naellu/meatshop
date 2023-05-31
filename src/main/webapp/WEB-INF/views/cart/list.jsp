<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

	<my:navBar></my:navBar>
	
	<h3 style="text-align: center;">장바구니</h3>
	
	<div class="container-lg">
        <div class="row justify-content-center">
            <div class="col-8 col-md-8">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"><input type="checkbox" id="checkAll"></th>
                            <th scope="col">상품명</th>
                            <th scope="col">구매수량</th>
                            <th scope="col">상품가격</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cartItem" items="${cartItems}">
                            <tr>
                                <td><input type="checkbox" id="" class="item-check cartItemId" value="${cartItem.id}" data-total-price="${cartItem.productPrice * cartItem.quantity}"></td>
                                <td id="productName_${cartItem.id}">${cartItem.productName}</td>
                                
			                    <td>
	                                <button type="button" class="btn btn-secondary btn-quantity-decrease" value="${cartItem.id}">-</button>
				                    <input type="number" id="quantity_${cartItem.id}" class="quantity-input" value="${cartItem.quantity}" min="1" readonly>
				                    <button type="button" class="btn btn-secondary btn-quantity-increase" value="${cartItem.id}">+</button>
			                    </td>
			                    
			                    <td id="productPrice_${cartItem.id}" data-unit-price="${cartItem.productPrice}">${cartItem.productPrice}</td>
                                <td>
	                                <button type="button" id="deleteButton" class="btn btn-danger btn-delete" value="${cartItem.id}">
	                                	<i class="fa-solid fa-xmark"></i>
	                                </button>
                                </td>
                            </tr>
			                    <input type="hidden" id="productId_${cartItem.id}" value="${cartItem.productId }"/>
                        </c:forEach>
			                    <input type="hidden" name="memberId" id="memberId" value="${memberId }"/>
                    </tbody>
                </table>
		            <button id="orderButton" class="btn btn-primary">주문하러가기</button>
		            <span id="totalPrice"></span>
            </div>
        </div>
    </div>
	


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="/js/cart/list.js"></script>

</body>
</html>