package com.example.demo.service.mail;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailService {

	MimeMessage createEmail(String email) throws MessagingException, UnsupportedEncodingException;
	
	String createKey();

	String sendEmail(String email) throws Exception;

}
