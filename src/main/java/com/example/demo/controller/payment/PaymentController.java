package com.example.demo.controller.payment;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.payment.CancelDataDto;
import com.example.demo.domain.payment.PaymentVerifyDto;
import com.example.demo.service.order.OrderService;
import com.example.demo.service.payment.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentController {
	
	@Value("${iamport.rest.api.key}")
	private String apiKey;
	
	@Value("${iamport.rest.api.secret}")
	private String apiSecret;
	
	private final OrderService orderService;
	private final PaymentService paymentService;
	
	private IamportClient iamportClient;
	
	@PostConstruct
	public void initIamportClient() {
		this.iamportClient = new IamportClient(apiKey, apiSecret);
	}
	
	// 액세스 토큰 생성 및 결제정보 가져오기
	@PostMapping("/payment/complete")
	@Transactional
	public ResponseEntity<Payment> prepare(@RequestBody Map<String, String> paymentData) throws IamportResponseException, IOException {
		// imp_uid, merchant_uid 꺼내오기
		String imp_uid = paymentData.get("imp_uid");
		String merchant_uid = paymentData.get("merchant_uid");
		
		// 액세스 토큰을 가지고 있는 객체 생성 
		IamportResponse<AccessToken> responseHasToken = iamportClient.getAuth();
		// 액세스 토큰 꺼내기
		String token = responseHasToken.getResponse().getToken();
		
		// 결제 정보 가져오기
		IamportResponse<Payment> paymentResponse = iamportClient.paymentByImpUid(imp_uid);
		Payment paymentInfo = paymentResponse.getResponse();
		
		// 결제 정보 저장하기
		paymentService.savePayment(paymentInfo);
		
		return new ResponseEntity<>(paymentInfo, HttpStatus.OK);
	}
	
	// 결제금액과 DB에 있는 주문금액 검증
	@PostMapping("/payment/verify")
	@Transactional
	public ResponseEntity<Boolean> postVerify(@RequestBody PaymentVerifyDto payment) {
		
		// 결제된 금액
		Integer paymentAmount = payment.getAmount().intValue();
		Integer orderId = Integer.parseInt(payment.getMerchantUid());

		// DB에서 결제 되어야 하는 금액 
		Integer orderAmountInDB = orderService.findRequiredPaymentData(orderId).getTotalPrice();
		
		if(paymentAmount.equals(orderAmountInDB)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	// 사전 검증
	@PostMapping("/payments/prepare")
	@Transactional
	public void preVerify(@RequestBody PaymentVerifyDto paymentVerifyDto) {
		try {
			
			PrepareData prepareData = new PrepareData(
					paymentVerifyDto.getMerchantUid(),
					paymentVerifyDto.getAmount());
			iamportClient.postPrepare(prepareData);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IamportResponseException e) {
			e.printStackTrace();
		}
	}
	
	// 미완성 로직
	@PostMapping("/payment/cancel")
	@Transactional
	public void cancelPayment(@RequestBody CancelDataDto cancelDataDto) throws IamportResponseException, IOException {
//		CancelData cancelData = new CancelData(cancelDataDto.getImpUid(), true);
		CancelData cancelData = new CancelData(cancelDataDto.getMerchantUid(), false);
		IamportResponse<Payment> result = iamportClient.cancelPaymentByImpUid(cancelData);
		Payment canceledPayment = result.getResponse();
		
		if(canceledPayment.getStatus().equals("cancelled")) {
			paymentService.changePaymentStatus(canceledPayment);
		}
		
	}
	
}
