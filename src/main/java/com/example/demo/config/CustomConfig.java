package com.example.demo.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.*;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.s3.*;

@Configuration
@EnableMethodSecurity
public class CustomConfig {

	@Value("${aws.accessKeyId}")
	private String accessKey;

	@Value("${aws.secretAccessKey}")
	private String secretKey;

	@Autowired
	private ServletContext application;

	@Value("${aws.bucketUrl}")
	private String bucketUrl;

	@PostConstruct
	public void init() {
		application.setAttribute("bucketUrl", bucketUrl);
	}

  
	@Bean
	public S3Client S3Client() {
		AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
		AwsCredentialsProvider provider = StaticCredentialsProvider.create(credentials);
		Region region = Region.AP_NORTHEAST_2;

		S3Client s3client = S3Client.builder()
				.credentialsProvider(provider)
				.region(region)
				.build();

		return s3client;
  }
  

  @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.formLogin(Customizer.withDefaults());   기본 설정( 안하면 페이지마다 로그인)
		http.formLogin().loginPage("/member/login"); // 해당 로그인 페이지 만들어야함 
		http.logout().logoutUrl("/member/logout");//로그아웃 페이지 변경(새로 만들어야함)
		return http.build();

	}

}
