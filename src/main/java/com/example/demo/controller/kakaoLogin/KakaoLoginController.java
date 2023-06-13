package com.example.demo.controller.kakaoLogin;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.kakaoLogin.*;

import jakarta.servlet.http.*;

// kauth.kakao.com/oauth/authorize?client_id=d7f3b956bef7e076113cc3f6f070b65b&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code

@Controller
@RequestMapping("/kakao")
public class KakaoLoginController {

	@Autowired
	private KakaoLoginService service;
	
	@GetMapping("/login")
	public String login(@RequestParam String code, HttpSession session) throws Exception {
		String accessToken = service.getAccessToken(code);
		HashMap<String, Object> userInfo = service.getuserInfo(accessToken);
		System.out.println("login Controller : " + userInfo);

		if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", accessToken);
	    }
		
		return "member/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    service.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "member/login";
	}
	
}
