package com.example.demo.service.kakaoLogin;

import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.domain.kakaoLogin.*;
import com.example.demo.mapper.kakaoLogin.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.google.gson.*;

@Service
public class KakaoLoginService {
	
	@Autowired
	private KakaoLoginMapper mapper;

	public String getAccessToken(String code) throws Exception {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");

			sb.append("&client_id=d7f3b956bef7e076113cc3f6f070b65b"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&redirect_uri=http://localhost:8080/kakao/login"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);

			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			access_Token = jsonMap.get("access_token").toString();
			refresh_Token = jsonMap.get("refresh_token").toString();

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	public HashMap<String, Object> getuserInfo(String access_Token) {
		HashMap<String, Object> userInfo = new HashMap<String, Object>();

		String requestURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(requestURL); // 1.url 객체만들기
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 2.url 에서 url connection 만들기
			conn.setRequestMethod("GET"); // 3.URL 연결구성
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			// 키값, 속성 적용
			int responseCode = conn.getResponseCode(); // 서버에서 보낸 http 상태코드 반환
			BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// 버퍼를 사용하여 일근ㄴ것
			String line = "";
			String result = "";
			while ((line = buffer.readLine()) != null) {
				result += line;
			}
			// readLine()) ==> 입력 String 값으로 리턴값 고정

			// 읽엇으니깐 데이터꺼내오기
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result); // Json element 문자열변경
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			userInfo.put("nickname", nickname);
			userInfo.put("email", email);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfo;
	}
	
	public void kakaoLogout(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
