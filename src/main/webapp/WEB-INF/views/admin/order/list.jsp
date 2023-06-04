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
<title>주문 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

	<my:navBar />

	<my:alert />
	
	<!-- Header-->
	<my:header>
		관리자 주문 관리
	</my:header>
	
	<br>
	
	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col-12">
			
				<table class="table">
			       <thead>
                       <tr>
	                       <th>주문번호</th>
	                       <th>회원</th>
	                       <th>상품명</th>
	       				   <th>총 결제금액</th>
	                       <th>주문일자</th>
	                       <th>주문상태</th>
                       </tr>
                   </thead>
                   <tbody>
				    	<c:forEach var="order" items="${orderList }">
					        <tr>
					            <td>${order.id }</td>
					            <td>${order.memberId }</td>
					            <td>
					            <c:forEach var="product" items="${order.productName}">
									<p>${product}</p>
								</c:forEach>
								</td>
					            <td>${order.totalPrice }</td>
					            <td>${order.created }</td>
					            <td>${order.status }</td>
					            <td> <!-- 주문상태 -->
					            	<c:choose>
					            		<c:when test="${order.status != 'CANCEL' }">
								            <button type="button" id="cancelOrderButton" class="btn btn-outline-danger cancelOrderButton" data-order-id="${order.id}">취소</button>
					            		</c:when>
					            		<c:otherwise>
					            			<!-- empty -->
					            		</c:otherwise>
					            	</c:choose>
					            </td>
					        </tr>
					    </c:forEach>
					</tbody>
			    </table>

			</div>
		</div>	
	</div>
	

	<my:footer/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src=""></script>
</body>
</html>