<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
	<form method="post" enctype="maultipart/form-date">
		<div class="mb-3">
		<input type="hidden" name="inquiryId" id="inquiryId" value="${productInquiry.inquiryId }"/>
		<input type="hidden" name="productId" id="productId" value="${productInquiry.productId}"/>
		</div>
		<div class="mb-3">
		<input type="hidden" name="customerId" id="customerId" value="${productInquiry.customerId }" />
		</div>
		<div class="mb-3">
		제목 : <input class="form-control" type="text" name="inquiryTitle" id="inquiryTitle" value="${productInquiry.inquiryTitle }"/><br />
		문의 내용
		<textarea class="form-control" id="bodyTextarea" rows="10" name="inquiryText">${productInquiry.inquiryText }</textarea>
		<br />
		비공개 질문하기
		<c:choose>
			<c:when test="${productInquiry.disclosure }">
			    <input type="checkbox" id="disclosure" name="disclosure">
			</c:when>
			<c:otherwise>
			    <input type="checkbox" id="disclosure" name="disclosure" checked="checked">
			</c:otherwise>
		</c:choose>
		 <br /> 
		</div>
		<input class="btn btn-outline-primary" type="button" id="modifyInquiryBtn" value="수정" />
		<button class="btn btn-outline-secondary" type="button" onclick="location.href='/product/info/${productInquiry.productId}'" >취소</button>
		
	</form>

