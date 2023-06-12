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
		</div>
		<div class="mb-3 form-floating " >
		문의 내용 <br />
		<textarea class="form-control" id="bodyTextarea" rows="10" name="inquiryText">${productInquiry.inquiryText }</textarea>
		<br />
		</div>
		<input class="btn btn-primary" type="button" id="modifyInquiryBtn" value="수정하기" />
		<button class="btn btn-secondary" type="button" onclick="location.href='/product/info/${productInquiry.productId}'" >취소</button>
		
	</form>

