package com.example.demo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
public class MailConfig {

	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.smtp.auth")
	private String auth;

	@Value("${spring.mail.smtp.starttls.enable")
	private String starttls;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);

		Properties properties = javaMailSender.getJavaMailProperties();
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.debug", "false"); // debug true로하면 설정정보 출력됨
		properties.put("mail.smtp.ssl.enable", "true");

		return javaMailSender;
	}
}
