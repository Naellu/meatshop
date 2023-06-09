<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<form action="/product/reviewResponse/modify" method="post">
	<div class="input-group">
	<div class="form-floating">
		<input type="hidden" name="responseId" value="${response.responseId }"/>
		<input type="hidden" name="productId" value="${productId }"/>
		<textarea style="height: 97px" class="form-control"
			name = "response">${response.response }</textarea>
	</div>
	<button class="btn btn-outline-primary">수정하기</button>
	</div>

</form>
