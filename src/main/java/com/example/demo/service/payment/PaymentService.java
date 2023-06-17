package com.example.demo.service.payment;

import com.siot.IamportRestClient.response.Payment;

public interface PaymentService {
	
	Integer savePayment(Payment payment);
	
	Integer changePaymentStatus(Payment payment);
	
}
