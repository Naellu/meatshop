<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageNumber" %>
<%@ attribute name="productId" %>
<%@ attribute name="customerId" %>

<c:url value="/product/inquiry/list" var="pageLink">
	<c:param name="page" value="${pageNumber }"></c:param>
	<c:param name="productId" value="${productId}"></c:param>
	<c:param name="customerId" value="${customerId}"></c:param>
	
</c:url>
<li class="page-item">
	<a name="pageBtn" class="page-link ${pageNumber eq pageInfo.currentPageNumber ? 'active' : '' }"
		data-product-id="${productId}"
		data-customer-id="${customerId }"
		data-page="${pageNumber }"
	>
		<jsp:doBody></jsp:doBody>
	</a>
</li>