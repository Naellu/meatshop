<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
    	.table thead th {
			text-align: left;
		}

		.table tbody td {
			text-align: left;
		}

		.table tbody p {
			text-align: left;
		}
		.accordion-button {
			padding: 0 !important;
		}
		.accordion-body {
			padding-left: 0 !important;
			padding-right: 0 !important;	
		}
		.custom-list-item {
		    max-width: 200px;
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		}
		
		#statusCount {
		    width: fit-content; /* 요소의 너비를 내용에 맞게 조절합니다. */
		    margin: 0 auto;      /* 좌우 마진을 자동으로 설정하여 가운데 정렬합니다. */
		}
    </style>
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
	
	
	<br>
	
	<div class="container-lg">
		<div class="row justify-content-center">

			<div class="col">
			
			<!-- 검색 -->
			<form action="/admin/order/list" class="d-flex" role="search">
				<div class="input-group">
					<select class="form-select flex-grow-0" style="width: 100px;" name="type" id="">
						<option value="all">전체</option>
						<option value="member" ${param.type eq 'title' ? 'selected' : '' }>회원</option>
						<option value="product" ${param.type eq 'body' ? 'selected' : '' }>상품명</option>
					</select>

					<input value="${param.search }" name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</form>
			
			<!-- 주문 정보 -->
				<table class="table">
			       <thead>
                       <tr>
	                       <th>주문번호</th>
	                       <th>회원</th>
	                       <th>상품명</th>
	       				   <th>총 결제금액</th>
	                       <th>주문일자</th>
	                       <th>주문상태</th>
	                       <th></th>
                       </tr>
                   </thead>
                   <tbody class="table-group-divider">
				    	<c:forEach var="order" items="${orderList }">
					        <tr>
					            <td>${order.id }</td>
					            <td>${order.memberId }</td>
					            <td>
					                <c:choose>
								        <c:when test="${fn:length(order.productName) == 1}">
								            <p>${order.productName[0]}</p>
								        </c:when>
								        <c:otherwise>
								            <div class="accordion accordion-flush" id="accordion${order.id}">
								                <div class="accordion-item">
								                    <div class="accordion-header" id="headingOne${order.id}">
								                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne${order.id}" aria-expanded="true" aria-controls="collapseOne${order.id}">
								                            ${order.productName[0]}
								                        </button>
								                    </div>
								                    <div id="collapseOne${order.id}" class="accordion-collapse collapse" aria-labelledby="headingOne${order.id}" data-bs-parent="#accordion${order.id}">
								                        <div class="accordion-body">
								                            <c:forEach var="product" begin="1" items="${order.productName}">
								                                <p>${product}</p>
								                            </c:forEach>
								                        </div>
								                    </div>
								                </div>
								            </div>
								        </c:otherwise>
								    </c:choose>
					            </td>
					            <td>${order.totalPrice }</td>
					            <td>${order.created }</td>
					            <td>
									<select class="form-select status-select" data-order-id="${order.id}">
										<option value="CREATED" ${order.status eq 'CREATED' ? 'selected' : '' }>주문완료</option>
										<option value="PREPARE" ${order.status eq 'PREPARE' ? 'selected' : '' }>배송준비</option>
										<option value="DELIVERY" ${order.status eq 'DELIVERY' ? 'selected' : '' }>배송중</option>
										<option value="COMPLETE" ${order.status eq 'COMPLETE' ? 'selected' : '' }>배송완료</option>
										<option value="CONFIRM_PURCHASE" ${order.status eq 'CONFIRM_PURCHASE' ? 'selected' : '' }>구매확정</option>
										<option value="CANCEL" ${order.status eq 'CANCEL' ? 'selected' : '' }>주문취소</option>
									</select>
								</td>
					        </tr>
					    </c:forEach>

					</tbody>
			    </table>
			</div>
			<div class="col-auto">
				<div id="statusCount">
					<ul class="list-group">
					    <c:forEach var="entry" items="${statusCount}">
					        <li class="list-group-item d-flex justify-content-between align-items-center custom-list-item">
					            ${entry.key}
					            <span class="badge bg-dark rounded-pill">${entry.value}</span>
					        </li>
					    </c:forEach>
					</ul>
				</div>
			</div>
		</div>	
	</div>
	
	
	<!-- 페이지네이션 -->
	<div class="container-lg">
		<div class="row">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">

					<!-- 이전 버튼 -->
					<c:if test="${pageInfo.currentPageNum gt 1 }">
						<my:adminOrderPageInfo pageNum="${pageInfo.currentPageNum - 1 }">
							<i class="fa-solid fa-angle-left"></i>
						</my:adminOrderPageInfo>
					</c:if>

					<c:forEach begin="${pageInfo.leftPageNum }" end="${pageInfo.rightPageNum }" var="pageNum">
						<my:adminOrderPageInfo pageNum="${pageNum }">
							${pageNum }
						</my:adminOrderPageInfo>
					</c:forEach>

					<!-- 다음 버튼 -->
					<c:if test="${pageInfo.currentPageNum lt pageInfo.lastPageNum }">
						<%-- 페이지 번호 : ${pageInfo.currentPageNum + 1 } --%>
						<my:adminOrderPageInfo pageNum="${pageInfo.currentPageNum + 1 }">
							<i class="fa-solid fa-angle-right"></i>
						</my:adminOrderPageInfo>
					
					</c:if>

				</ul>
			</nav>
		</div>
	</div>
	
	

	<my:footer/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/js/admin/order/list.js"></script>
</body>
</html>