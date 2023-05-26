<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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

	<h3 style="text-align: center;">주문 상세</h3>
	
	<br>
	
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12 col-md-8">
			
				<form id="orderDetailForm" action="/order/detail" method="post" style="text-align: center;">
					
					<input type="hidden" name="memberId" value="${member.id}" />
			
					<table class="table">
				        <thead>
				            <tr>
				                <th>상품id</th>
				                <th>상품명</th>
				                <th>상품가격</th>
				                <th>수량</th>
				                <th>원산지</th>
				                <th>카테고리</th>
				            </tr>
				        </thead>
				        <tbody>
				            <c:forEach var="product" items="${product }">
					          <tr>
					              <td>${product.productId }</td>
					              <td>${product.productName }</td>
					              <td>${product.price }</td>
					              <td>${product.stockQuantity }</td>
					              <td>${product.countryOfOrigin }</td>
					              <td>${product.categoryId }</td>
				              		<td>
				                        <input type="hidden" name="productId" value="${product.productId}" />
					                    <input type="hidden" name="quantity" value="${quantity }" />
				                  	</td>
					          </tr>
					      </c:forEach>
				        </tbody>
				    </table>
				    
			        <button type="submit" class="btn btn-primary">결제하기</button>
			    </form>
			
			</div>
		</div>	
	</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>