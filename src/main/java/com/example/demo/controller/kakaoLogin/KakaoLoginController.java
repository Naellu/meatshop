package com.example.demo.controller.kakaoLogin;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import com.example.demo.domain.*;
import com.example.demo.domain.kakaoLogin.*;
import com.example.demo.service.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

// 40분 https://www.youtube.com/watch?v=WACDTFroYNI
@Controller
@JsonIgnoreProperties(ignoreUnknown = true)
@RequestMapping("/auth")
public class KakaoLoginController {
//	@Value("${kakao.login.client.secret}")
//	private String clientsecret;
	

	@Autowired
	private MemberService service;

	@Value("${cos.key}")
	private String cosKey;

	@GetMapping("/kakao/callback")
	public String kakaoCallback(String code) {
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

		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
		}

		System.out.println("카카오 액세스 토큰: " + oauthToken.getAccess_token());
		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET,
				kakaoProfileRequest2, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 아이디(번호): " + kakaoProfile.getId());
		System.out.println("카카오 이메일: " + kakaoProfile.getKakao_account().getEmail());

		System.out.println("쇼핑몰서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
		System.out.println("쇼핑몰서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		System.out.println("쇼핑몰서버 패스워드 : " + cosKey);

		Members member = new Members();

		
		 Members kakaoMember = Members.builder()
		            .id(kakaoProfile.getKakao_account().getEmail())
		            .name(kakaoProfile.getProperties().nickname)
		            .password(cosKey.toString())
		            .email(kakaoProfile.getKakao_account().getEmail())
		            .address("dd")
		            .birthday("2000-01-14")
		            .phoneNumber("010-4156-8966")
		            .oauth("kakao")
		            .authority(null)
		            .build();

		    // 가입자 혹은 비가입자 체크 해서 처리
		    Members originMember = service.findMember(kakaoMember.getId());

		    if (originMember == null) {
		        System.out.println("기존 회원이 아닙니다..");
		        service.signup(kakaoMember);
		    }

		    Members loggedInMember = service.findMember(kakaoMember.getId());

		    List<GrantedAuthority> authorityList = new ArrayList<>();
		    List<String> authorityStringList = kakaoMember.getAuthority();
		    if (authorityStringList != null) {
		        for (String auth : authorityStringList) {
		            authorityList.add(new SimpleGrantedAuthority(auth));
		        }
		    }

		    System.out.println("자동 로그인을 합니다.");

		    // 로그인 처리

	    return "redirect:/";
	}

}
