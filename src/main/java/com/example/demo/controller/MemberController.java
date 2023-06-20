package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Members;
import com.example.demo.service.MemberService;
import com.example.demo.service.mail.MailService;
import com.example.demo.service.question.QuestionService;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MailService mailService;
	
	@Autowired
	private QuestionService questionService;
	
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
			rttr.addFlashAttribute("message", "회원 가입되었습니다.");
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("member", member); // 문제 발생해도 해당 칸에 적은 정보 유지
			rttr.addFlashAttribute("message", "회원 가입 중 문제가 발생했습니다.");
			return "redirect:/member/signup";
		}
	}

// 마이페이지 (내정보 확인란)
	@GetMapping("info")
//	@PreAuthorize("isAuthenticated() and (authentication.name eq #id) or hasAuthority('admin')") 
	public void info(String id, Model model) {

		Members member = service.get(id);
		
		model.addAttribute("member", member);
	}

// 마이페이지 - 수정	
	@GetMapping("modify")
	@PreAuthorize("isAuthenticated()")
	public void modifyForm(String id, Model model) {

		Members member = service.get(id);
		System.out.println(member);
		model.addAttribute("member", member);
	}

	@PostMapping("modify")
	@PreAuthorize("isAuthenticated() and (authentication.name eq #member.id)")
	public String modifyProcess(Members member, RedirectAttributes rttr, String oldPassword) {
		boolean ok = service.modify(member, oldPassword);
		if (ok) {
			rttr.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
			return "redirect:/member/info?id=" + member.getId();
		} else {
			rttr.addFlashAttribute("message", "회원 정보 수정시 문제가 발생하였습니다.");
			return "redirect:/";
		}

	}

	@PostMapping("remove")
//	@PreAuthorize("isAuthenticated() and (authentication.name eq #member.id)") //remove메소드의 Member파라미터의 id값을 받아옴
	public String remove(Members member, RedirectAttributes rttr,
			HttpServletRequest request) throws ServletException {

		boolean ok = service.remove(member);
		if (ok) {
			rttr.addFlashAttribute("message", "탈퇴완료");
			// 로그아웃
			request.logout();
			return "redirect:/";

		} else {
			rttr.addFlashAttribute("message", "탈퇴 문제 발생");
			return "redirect:/member/mypage?id=" + member.getId();

		}
	}

	@GetMapping("checkId/{userid}")
	@ResponseBody // 객체를 json으로 넘겨줌
	public Map<String, Object> checkId(@PathVariable("userid") String id) {
		return service.checkId(id);
	}
	// ------------------- memberList 페이지네이션 ------------------------

//	@RequestMapping({"/","list"}, method = RequestMethod.GET)
	@GetMapping({ "/", "list" }) // "/" ,"list" 경로로 들어온 get요청 처리
	public String list(Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "type", required = false) String type) {

		// 1. request param 수집 / 가공
		// 2. business logic 처리
//		List<Members> memberlist = service.listMember();
//		model.addAttribute("memberlist", memberlist);
//		List<Board> list = service.listBoard();
		Map<String, Object> list = service.listBoard(page, search, type);
		// 3. add attribute

		// model.addAttribute("boardList",list.get("boardList"));
		model.addAllAttributes(list);

		// 4. forward/ redirect

		return "member/list";
	}

	@GetMapping("mypage")
//	@PreAuthorize("isAuthenticated() and (authentication.name eq #id) or hasAuthority('admin')") 
	public void mypage(String id, Model model) {

		Members member = service.get(id);
		model.addAttribute("member", member);
	}

	@GetMapping("checkEmail/{useremail}")
	@ResponseBody // 객체를 json으로 넘겨줌
	public Map<String, Object> checkemail(@PathVariable("useremail") String id) {
		return service.checkEmail(id);
	}

	@PostMapping("/mailCheck")
	@ResponseBody
	String mailConfirm(@RequestParam("email") String email) throws Exception {
		String authCode = mailService.sendEmail(email);
		return authCode;
	}
	
// ------------------------------ 아이디 찾기 로직 ----------------------------------	
	
	@GetMapping("searchId")
	public void findId() {
		
	}
		
	@PostMapping("searchId/{checkEmail}")
	@ResponseBody
	public String resultId(@PathVariable("checkEmail")String email,HttpSession session) throws Exception {
		
	String authCode = mailService.sendEmail(email);
	System.out.println(authCode);//전송되는 코드 번호
		session.setAttribute("authCode", authCode); //다음 메소드에서 사용하기 위해 세션에 저장함
		return null;
	}
	
	
	
	
	@PostMapping("checkCode/{userCode}")
	@ResponseBody
	public boolean checkCode(@PathVariable("userCode")String code,HttpSession session) throws Exception {
		
	String userCode = code;
	String authCode = (String) session.getAttribute("authCode");

	System.out.println(service.checkCode(userCode,authCode));
	return service.checkCode(userCode,authCode);
	
	}
	
	
	@PostMapping("findId")
	@ResponseBody
	public Members lookup(Members member,@RequestParam("email") String email) {
		Members userId = service.getId(email);
		System.out.println(userId);
		return userId;
	}
	// ------------------------------ 아이디 찾기 로직 끝 ----------------------------------	
	// ------------------------------ 비밀번호 찾기 로직 시작 ----------------------------------
	@GetMapping("searchPassword")
	public void findPassword() {
		
	}
	
	
	@PostMapping("/searchPassword")
@ResponseBody
	public String searchPassword(@RequestParam("email") String email,@RequestParam("birthday") String birthday) {
		
		String tempPwd = service.searchPassword(email, birthday);
		if(tempPwd == "false") {
			return "false";
		}
		System.out.println(birthday);
		try {
			mailService.sendTempPwd(email, tempPwd);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
		// email 보내는 서비스 쓸것
		System.out.println(tempPwd + "controller");
		return "success";
	
	}
//	------------------------------ 아이디 찾기 로직 끝 ----------------------------------
	
	
	
	
	
}
