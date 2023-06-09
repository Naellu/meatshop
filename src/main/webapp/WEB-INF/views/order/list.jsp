<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.*"%>
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

.table td {
    vertical-align: middle;
}
	
.accordion-button {
	padding: 24px 0px !important;
}

.accordion-body {
	padding-left: 0 !important;
	padding-right: 0 !important;
}

.custom-list-item {
	max-width: 200px; /* 가로 길이를 원하는 크기로 조절 */
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

#statusCount {
	width: fit-content; /* 요소의 너비를 내용에 맞게 조절합니다. */
	margin: 0 auto; /* 좌우 마진을 자동으로 설정하여 가운데 정렬합니다. */
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
</style>
<title>내 주문목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<my:commonFont></my:commonFont>
</head>

<body>

	<my:navBar></my:navBar>

	<my:mypageHeader>
		${memberId}님의 주문목록
			<input type="hidden" id="memberId" value="${memberId}" />
		<!-- <input type="hidden" id="bucketUrl" value="${bucketUrl}" />  -->
	</my:mypageHeader>

	<br>



	<br>

	<div class="container-lg">
		<div class="row justify-content-center">
			<div class="col">
				<table class="table">
					<thead>
						<tr id="table-row">
							<th>주문번호</th>
							<th>상품명</th>
							<th>총 결제금액</th>
							<th>주문일자</th>
							<th>주문상태</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<c:forEach var="order" items="${orderList }">
							<tr>
								<td>${order.id }</td>
								<td><c:choose>
										<c:when test="${fn:length(order.productName) == 1}">
											<div class="product-item">
												<img class="card-img-top mb-5 mb-md-0 product-image" src="${bucketUrl}/product/${order.productIds[0] }/main.png" alt=""/>
													<p class="product-name">${order.productName[0]}
														<c:if test="${order.status != 'CANCEL' }">
															<button class="btn btn-outline-primary" type="button"
																name="addReviewButtons"
																data-product-id="${order.productIds[0] }"
																data-customer-id="${memberId }">리뷰쓰기</button>
														</c:if>
													</p>
											</div>
										</c:when>
										<c:otherwise>
											<div class="accordion accordion-flush"
												id="accordion${order.id}">
												<div class="accordion-item">
													<div class="accordion-header" id="headingOne${order.id}">

														<button class="accordion-button collapsed" type="button"
															data-bs-toggle="collapse"
															data-bs-target="#collapseOne${order.id}"
															aria-expanded="true"
															aria-controls="collapseOne${order.id}">
															${order.productName[0]} 외
															${fn:length(order.productName)-1}건</button>
													</div>
													<div id="collapseOne${order.id}"
														class="accordion-collapse collapse"
														aria-labelledby="headingOne${order.id}"
														data-bs-parent="#accordion${order.id}">
														<div class="accordion-body">
															<c:forEach begin="0" end="${fn:length(order.productName)-1}" var="i">
															<div class="product-item">
																<img class="card-img-top mb-5 mb-md-0 product-image" src="${bucketUrl}/product/${order.productIds[i] }/main.png" alt=""/>
																<p class="product-name">${order.productName[i]}
																	<c:if test="${order.status != 'CANCEL' }">
																		<button class="btn btn-outline-primary" type="button"
																			name="addReviewButtons"
																			data-product-id="${order.productIds[i] }"
																			data-customer-id="${memberId }">리뷰쓰기</button>
																	</c:if>
																</p>
															</div>
															</c:forEach>
														</div>
													</div>
												</div>
											</div>
										</c:otherwise>
									</c:choose></td>
								<td>${order.totalPrice }</td>
								<td>${order.created }</td>
								<td>${order.status.title }</td>
								<td>
									<!-- 주문상태 --> <c:choose>
										<c:when test="${order.status == 'CREATED' }">
											<button type="button" id="cancelOrderButton"
												class="btn btn-outline-danger cancelOrderButton"
												data-order-id="${order.id}">취소</button>
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
			<div class="col-auto">
				<div id="statusCount">
					<ul class="list-group">
						<c:forEach var="entry" items="${statusCount}">
							<li
								class="list-group-item d-flex justify-content-between align-items-center custom-list-item">
								${entry.key} <span class="badge bg-dark rounded-pill">${entry.value}</span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<my:footer></my:footer>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
		integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/order/list.js"></script>
</body>
</html>