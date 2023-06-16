package com.example.demo.service.mail;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailService {

	String sendEmail(String email) throws Exception;

	void sendNotifyEmail(Integer productId);

	void sendTempPwd(String email, String tempPwd) throws UnsupportedEncodingException, MessagingException;
	
	
	
	
	
	
}
