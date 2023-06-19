package com.example.demo.domain.kakaoLogin;

import com.fasterxml.jackson.annotation.*;

import lombok.*;
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class KakaoProfile {

	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	
	@Data
	public class Properties {
		public String nickname;
	}
	
	@Data
	public class KakaoAccount {
		public Boolean profile_needs_agreement;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
	}
}
