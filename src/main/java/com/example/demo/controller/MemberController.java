package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.demo.domain.Members;
import com.example.demo.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("login")
	@PreAuthorize("isAnonymous()")
	public void loginForm() {

	}
	
	@PostMapping("login")
	public String loginProcess() {
		
		return "redirect:/";
	}
	
	
	
	@GetMapping("signup")
	@PreAuthorize("isAnonymous()") 
	public void signupForm() {
	
	}
	
	
	@PostMapping("signup")
	public String signupProcess(Members member, RedirectAttributes rttr) {
		try {
			service.signup(member);
//			rttr.addFlashAttribute("message", "회원 가입되었습니다.");
//			return "redirect:/list";
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("member", member); // 문제 발생해도 해당 칸에 적은 정보 유지
//			rttr.addFlashAttribute("message", "회원 가입 중 문제가 발생했습니다.");
			return "redirect:/member/signup";
		}
	}
	
	//회원목록
	@GetMapping("list")
	public void list(Model model) {
		List<Members> list = service.listMember();
		model.addAttribute("memberList", list);
		
		
	}
	
//0522 마무리못함 login 기능도 작동 안함
	@GetMapping("mypage")
//	@PreAuthorize("isAuthenticated() and (authentication.name eq #id) or hasAuthority('admin')") 
	public void mypage(String id,Model model) {
		
		   Members member = service.get(id);
		   model.addAttribute("member", member);
	}
	
}
