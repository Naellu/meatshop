package com.example.demo.service.mail;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final JavaMailSender emailSender;

	private String authCode;

	@Override
	public MimeMessage createEmail(String email) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setTo(email);
		helper.setSubject("NiceToMeatYou 회원가입 이메일 인증");

		String msg = "<h1>안녕하세요</h1>\n"
				+ "<p>\n"
				+ "NiceToMeatYou입니다.\n"
				+ "<br />\n"
				+ "다음은 인증번호입니다.\n"
				+ "</p>\n"
				+ "<div style='font-size: 130%'>\n"
				+ "CODE : <strong>" + authCode + "</strong>\n"
				+ "</div>";
		helper.setText(msg, true);
		helper.setFrom(new InternetAddress("admin@nicetomeatyou.com", "관리자"));

		return message;
	}

	// 랜덤 인증 코드 전송
	@Override
	public String createKey() {
		Random random = new Random();
		int randomNumber = random.nextInt(90) + 10; // 10 이상 99 이하의 랜덤 숫자 생성
		return String.valueOf(randomNumber);
	}

	// 메일 발송
	// send 의 매개변수로 들어온 email 는 곧 이메일 주소가 되고,
	// MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
	// 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
	@Override
	public String sendEmail(String email) throws Exception {
		authCode = createKey(); // 랜덤 인증번호 생성

		MimeMessage message = createEmail(email); // 메일 발송
		try {// 예외처리
			//메일보내기
			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		return authCode; // 메일로 보냈던 인증 코드를 서버로 반환
	}
}
