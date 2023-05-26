package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Members;
import com.example.demo.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

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
		model.addAttribute("memberlist", list);
	}
	
	@GetMapping("membersearch")
	public String list (Model model,
//			@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam(value = "search",defaultValue= "")String search,
			@RequestParam(value = "type", required = false) String type) {
		
		Map<String, Object> list = service.list(search,type); 
		model.addAllAttributes(list);
		return "member/list";
	}
	
		
	
// 마이페이지 (내정보 확인란)
	@GetMapping("mypage")
//	@PreAuthorize("isAuthenticated() and (authentication.name eq #id) or hasAuthority('admin')") 
	public void mypage(String id,Model model) {
		
		   Members member = service.get(id);
		   model.addAttribute("member", member);
	}
	
	
	
// 마이페이지 - 수정	
	@GetMapping("modify")
	@PreAuthorize("isAuthenticated()")
	public void modifyForm(String id,Model model) {
		
		 Members member = service.get(id);
		 System.out.println(member);
		 model.addAttribute("member",member);
}
	
	
	@PostMapping("modify")
	@PreAuthorize("isAuthenticated() and (authentication.name eq #member.id)")
	public String modifyProcess(Members member, RedirectAttributes rttr,String oldPassword) {
		boolean ok = service.modify(member,oldPassword);
		System.out.println(oldPassword);
		System.out.println(member);
		if (ok) {
//			rttr.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
			return "redirect:/member/mypage?id=" + member.getId();
		} else {
//			rttr.addFlashAttribute("message", "회원 정보 수정시 문제가 발생하였습니다.");
//			return "redirect:/member/modify?id=" + member.getId();
			return "redirect:/";
		}
		
	}
	
	
	@PostMapping("remove")
//	@PreAuthorize("isAuthenticated() and (authentication.name eq #member.id)") //remove메소드의 Member파라미터의 id값을 받아옴
	public String remove(Members member,RedirectAttributes rttr,
			HttpServletRequest request) throws ServletException {
	
		System.out.println(member);
		boolean ok = service.remove(member);
		if(ok) {
//			rttr.addFlashAttribute("message","탈퇴완료");
			
			// 로그아웃
			request.logout();
			return "redirect:/";
			
		}else {
//			rttr.addFlashAttribute("message","탈퇴 문제 발생");
			return "redirect:/member/mypage?id=" + member.getId();
			
		}
	}
}
