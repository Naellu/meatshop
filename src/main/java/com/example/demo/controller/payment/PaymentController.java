package com.example.demo.controller.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.payment.PaymentDto;
import com.example.demo.service.order.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentController {
	
	@Value("{iamport.rest.api.key}")
	private String apiKey;
	
	@Value("{iamport.rest.api.secret}")
	private String apiSecret;
	
	private OrderService orderService;
	
	// 액세스 토큰 생성 및 결제정보 가져오기
	@PostMapping("/payment/complete")
	@Transactional
	public ResponseEntity<Payment> prepare(@RequestBody Map<String, String> paymentData) throws IamportResponseException, IOException {
		// imp_uid, merchant_uid 꺼내오기
		String imp_uid = paymentData.get("imp_uid");
		String merchant_uid = paymentData.get("merchant_uid");
		
		// api key와 secret으로 iamportClient 객체 생성
		IamportClient iamportClient = new IamportClient(apiKey, apiSecret);
		log.info("iamportClient IN COMPLETE={}",iamportClient);
		
		// 액세스 토큰을 가지고 있는 객체 생성 
		IamportResponse<AccessToken> responseHasToken = iamportClient.getAuth();
		// 액세스 토큰 꺼내기
		String token = responseHasToken.getResponse().getToken();
		
		log.info("AccessToken IN COMPLETE={}", token);
		
		// 결제 정보 가져오기
		IamportResponse<Payment> paymentResponse = iamportClient.paymentByImpUid(imp_uid);
		log.info("paymentResponse IN COMPLETE={}",paymentResponse);
		Payment paymentInfo = paymentResponse.getResponse();
		
		log.info("paymentInfo IN COMPLETE={}",paymentInfo);
		
		return new ResponseEntity<>(paymentInfo, HttpStatus.OK);
	}
	
	// 결제금액과 DB에 있는 주문금액 검증
	@PostMapping("/payment/verify")
	@Transactional
	public ResponseEntity<Boolean> verify(@RequestBody Payment payment) {
		
		// 결제된 금액
		Integer paymentAmount = payment.getAmount().intValue();
		log.info("paymentAmount IN VERIFY={}", paymentAmount);
		Integer orderId = Integer.parseInt(payment.getMerchantUid());

		// DB에서 결제 되어야 하는 금액 
		Integer orderAmountInDB = orderService.findRequiredPaymentData(orderId).getTotalPrice();
		log.info("orderAmountInDB IN VERIFY={}", orderAmountInDB);
		
		if(paymentAmount.equals(orderAmountInDB)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
}
