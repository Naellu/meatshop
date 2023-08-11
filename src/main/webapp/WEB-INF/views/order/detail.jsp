<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>주문 상세</title>
<style>
	#buttonDiv {
		display: flex;
		justify-content: center; 
		align-items: center; 
		height: 10vh;
	}
	
	#showPriceDiv {
		display: flex;
		justify-content: center; 
		align-items: center; 
	}
	#orderLable {
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
</head>

<body>

	<my:navBar></my:navBar>

	<h3 id="orderLable">주문 상세</h3>
	
	<br>
	
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8">
				<table class="table">
			        <thead>
			            <tr>
			                <th>상품명</th>
			                <th>상품가격</th>
			                <th>수량</th>
			            </tr>
			        </thead>
			        <tbody class="table-group-divider">
		        		<c:forEach var="orderItemDto" varStatus="status" items="${orderItemDtos}">
				          <tr>
				              <td>
				              	<div class="product-item">
					              <img class="card-img-top mb-5 mb-md-0 product-image" src="${bucketUrl}/product/${orderItemDto.productId }/main.png" alt=""/>
					              <p class="product-name">${productNames[status.index] }</p>
				              	</div>
				              </td>
				              <%--  <td><fmt:formatNumber value="${orderItemDto.price}" pattern="#,###"/></td> --%>
				              <td><fmt:formatNumber value="${orderItemDto.price}" maxFractionDigits="0"/></td>
				              <td>${orderItemDto.quantity }</td>
			              		<td>
									<input type="hidden" name="memberId" value="${orderItemDto.memberId }" />
			                        <input type="hidden" id="productId" name="productId" value="${orderItemDto.productId }" />
				                    <input type="hidden" id="quantity" name="quantity" value="${orderItemDto.quantity }" />
				                    <input type="hidden" id="price" name="price" value="${orderItemDto.price }" />
				                    <input type="hidden" id="fromCart" name="fromCart" value="${orderItemDto.fromCart }" />
			                  	</td>
				          </tr>
			          	</c:forEach>
			        </tbody>
			    </table>
			    
			    <div id="showPriceDiv">
				    <div id="totalPriceDisplay" class="float-right">
				    	총 주문금액: <span id="totalPrice" value="">0</span>₩
				    </div>
				</div>
				<div id="buttonDiv">
<%--			        <button id="simpleOrderButton" type="submit" class="btn btn-outline-dark">결제완료</button>--%>
			        <button id="paymentButton" type="submit" class="btn btn-outline-dark">결제하기</button>
			    </div>
			
			</div>
		</div>	
	</div>

	<my:footer></my:footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

<script src="/js/order/detail.js"></script>

</body>
</html>