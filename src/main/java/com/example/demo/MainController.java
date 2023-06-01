package com.example.demo;

import java.util.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.*;
import com.example.demo.service.customer.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	private final ProductService productService;

	@GetMapping
	public String mainPage() {
		return "main";
	}

	@GetMapping("listView")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listView() {
		Map<String, Object> result = productService.getTopView();
		return ResponseEntity.ok(result);
	}
}
