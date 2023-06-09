package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.customer.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	private final ProductService productService;

	@Value("${kakao.accessKey}")
	private String accessKey;

	@GetMapping
	public String main() {
		return "main";
	}

	@GetMapping("listView")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> listView() {
		Map<String, Object> result = productService.getTopView();
		return result;
	}

	@GetMapping("location")
	public String location(Model model) {
		model.addAttribute("kakaoMap", accessKey);
		return "customer/location";
	}
}

/*@GetMapping("listView")
@ResponseBody
public ResponseEntity<Map<String, Object>> listView() {
	Map<String, Object> result = productService.getTopView();
	return ResponseEntity.ok(result);
}
*/
