package com.example.demo.controller.kakaoLogin;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import com.example.demo.domain.kakaoLogin.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
// 40분 https://www.youtube.com/watch?v=WACDTFroYNI
@Controller
@JsonIgnoreProperties(ignoreUnknown=true)
@RequestMapping("/auth")
public class KakaoLoginController {
//	@Value("${kakao.login.client.secret}")
//	private String clientsecret;

	@GetMapping("/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {
	    RestTemplate rt = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "authorization_code");
	    params.add("client_id", "d7f3b956bef7e076113cc3f6f070b65b");
	    params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
	    params.add("code", code);
//	    params.add("client_secret", clientsecret);

	    HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

	    ResponseEntity<String> response = rt.exchange(
	            "https://kauth.kakao.com/oauth/token",
	            HttpMethod.POST,
	            kakaoTokenRequest,
	            String.class
	    );
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    OAuthToken oauthToken = null;
	    try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    System.out.println("카카오 액세스 토큰: " + oauthToken.getAccess_token());
	    RestTemplate rt2 = new RestTemplate();

	    HttpHeaders headers2 = new HttpHeaders();
	    headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
	    headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

	    HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

	    ResponseEntity<String> response2 = rt2.exchange(
	            "https://kapi.kakao.com/v2/user/me",
	            HttpMethod.GET,
	            kakaoProfileRequest2,
	            String.class
	    );

	    KakaoProfile kakaoProfile = new KakaoProfile();
	    System.out.println("카카오 아이디(번호): " + kakaoProfile.getId());
//	    System.out.println("카카오 이메일: " + kakaoProfile.getKakao_account().getEmail());
	    return response2.getBody();
	}

	
}
