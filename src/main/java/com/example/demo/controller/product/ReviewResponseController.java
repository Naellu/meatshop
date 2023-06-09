package com.example.demo.controller.product;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.domain.*;
import com.example.demo.service.product.review.*;

@Controller
@RequestMapping("product/reviewResponse")
public class ReviewResponseController {

	@Autowired
	private ReviewResponseService responseService;
	
	@PostMapping("add")
	public ResponseEntity<Map<String, Object>> addResponse(
			@RequestBody ReviewResponse reviewResponse){
		
		Map<String, Object> res = new HashMap<>();
		
		boolean ok = responseService.addResponse(reviewResponse);
		
		if(ok) {
			res.put("message", "답변이 완료되었습니다.");
			return ResponseEntity.ok().body(res); 
		} else {
			return ResponseEntity.status(403).build(); 
		}
		
	}
	
	@PostMapping("remove")
	public String remove(
			Integer responseId,
			Integer productId,
			RedirectAttributes rttr) {
		boolean ok = responseService.removeResponseById(responseId);
		if(ok) {
			rttr.addFlashAttribute("message", "답변이 삭제되었습니다..");
			return "redirect:/product/info/" + productId;
		} else {
			rttr.addFlashAttribute("message", "(오류) 답변이 삭제되지 않았습니다.");
			return "redirect:/product/info/" + productId;
		}
	}
	
	@GetMapping("modify")
	public String modifyForm(Integer responseId, Integer productId, Model model) {
		model.addAttribute("response", responseService.getResponse(responseId));
		model.addAttribute("productId", productId);
		return "product/reviewResponse/modify";
	}
	
	@PostMapping("modify")
	public String modifyResponse(
			ReviewResponse reviewResponse,
			Integer productId,
			RedirectAttributes rttr){

		
		boolean ok = responseService.modifyResponse(reviewResponse); 
		
		if(ok) {
			rttr.addFlashAttribute("message", "답변이 수정되었습니다.");
			return "redirect:/product/info/" + productId;
		} else {
			rttr.addFlashAttribute("message", "(오류)답변이 수정되지 않았습니다.");
			return "redirect:/product/info/" + productId;
		}
		
	}
}
