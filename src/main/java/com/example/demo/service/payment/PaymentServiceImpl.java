package com.example.demo.service.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.payment.PaymentMapper;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentMapper paymentMapper;

	// 결제 정보 저장
	@Override
	public Integer savePayment(Payment payment) {
		return paymentMapper.savePaymentInfo(payment);
	}
	

}
