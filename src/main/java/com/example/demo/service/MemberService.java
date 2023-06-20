package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Members;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.service.mail.MailService;






import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService {

	
	@Autowired
	private MailService mailService;
	
	
	@Autowired
	private MemberMapper mapper;

	// 암호화 작업, 회원가입 ---------------------------------------
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean signup(Members member) {
		String plain = member.getPassword();
		member.setPassword(passwordEncoder.encode(plain));

		int cnt = mapper.insert(member);
		return cnt == 1;

	}
	// -----------------------------------------------------

	public Members get(String id) {
		return mapper.selectId(id);
	}

	public List<Members> listMember() {
		return mapper.selectALL();
	}

	public boolean modify(Members member, String oldPassword) {

		if (!member.getPassword().isBlank()) {
			String plain = member.getPassword();
			member.setPassword(passwordEncoder.encode(plain));
		}

		Members oldMember = mapper.selectId(member.getId());

		int cnt = 0;
		if (passwordEncoder.matches(oldPassword, oldMember.getPassword())) {

			cnt = mapper.update(member);
		}

		return cnt == 1;
	}

	public boolean remove(Members member) {
		Members oldMember = mapper.selectId(member.getId());
		System.out.println(oldMember);
		System.out.println(member);
		int cnt = 0;

		if (passwordEncoder.matches(member.getPassword(), oldMember.getPassword())) {
			// 입력한 암호와 기존 암호가 같으면?

			// 이 회원이 작성한 게시물 row 삭제
//			boardService.removeByMemberIdWriter(member.getId());

			// 이 회원이 좋아요한 레코드 삭제
//			likeMapper.deleteByMemberId(member.getId());

			// 회원 테이블 삭제
			cnt = mapper.deleteById(member.getId());
		}

		return cnt == 1;
	}

	public Map<String, Object> list(String search, String type) {
		List<Members> list = mapper.selectMember(search, type);
		System.out.println(list);
		return Map.of("memberlist", list);
	}

	public Map<String, Object> checkId(String id) {
		Members member = mapper.selectId(id);

		return Map.of("available", member == null);
	}

	public Map<String, Object> checkEmail(String id) {
		Members member = mapper.selectEmail(id);

		return Map.of("available", member == null);
	}

	// ----------------- 이메일로 아이디 찾기-----------------------------------
	public Map<String, Object> findEmail(String id) {
		Members member = mapper.selectEmail(id);
		return Map.of("available", member == null);
	}

	// ----------------------------------------------------

	// ------------------- memberList 페이지네이션 ------------------------
	public Map<String, Object> listBoard(Integer page, String search, String type) {
		// 페이지당 행의 수
		Integer rowPerPage = 5;

		// 쿼리 LIMIT 절에 사용할 시작 인덱스
		Integer startIndex = (page - 1) * rowPerPage;

		// 페이지네이션이 필요한 정보
		// 전체 레코드 수
		Integer numOfRecords = mapper.countAll(search, type);
		System.out.println(numOfRecords);
		// 마지막 페이지 번호
		Integer lastPageNumber = (numOfRecords - 1) / rowPerPage + 1;
		// 페이지네이션 왼쪽번호
		Integer leftPageNum = page - 3;
		// 1보다 작을 수 없음
		leftPageNum = Math.max(leftPageNum, 1);

		// 페이지네이션 오른쪽번호
		Integer rightPageNum = leftPageNum + 4;
		// 마지막페이지보다 클 수 없음
		rightPageNum = Math.min(rightPageNum, lastPageNumber);

		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("rightPageNum", rightPageNum);
		pageInfo.put("leftPageNum", leftPageNum);
		pageInfo.put("currentPageNum", page);
		pageInfo.put("lastPageNum", lastPageNumber);

		// 게시물 목록
		List<Members> list = mapper.selectAllPaging(startIndex, rowPerPage, search, type);
		System.out.println(list);
		return Map.of("pageInfo", pageInfo, "memberList", list);
	}

	// ----------------- 아이디찾기 이메일 인증, Id 가져오는 메소드 -----------------------------
	public boolean checkCode(String code, String authCode) {
		log.info("authCode IN SERVICE={}", authCode);
		if (code.equals(authCode)) {
			return true;
		} else {

			return false;
		}
	}

	public Members getId(String email) {
		System.out.println(email);
		return mapper.getMemberId(email);
	}
// ----------------------------------------------------------------------------------------------------
//	------------------------------ 비밀번호 찾기 로직 ----------------------------------
	public String searchPassword(String email, String birthday) {
		// DB에서 이메일과 생일을 확인하여 일치하는 사용자가 있는지 검증하는 로직
		// 일치하면 true, 일치하지 않으면 false 반환
		String a = mapper.findPassword(email, birthday);
		System.out.println(a + "service");
		System.out.println(email);
		System.out.println(birthday);
		
		if (a != null && a.length() >= 1) {
			String newPassword = generateRandomPassword(); //새로운 비밀번호 생성
				
			Integer x = mapper.updatePassword(passwordEncoder.encode(newPassword), email, birthday); // 새로운 비밀번호 암호화
			System.out.println(newPassword);
			System.out.println(x);
			return newPassword;

			// else 부분 마무리 하기 사용자가 잘못된 이메일,생년월일 입력하면 오류코드뜸
	
		} else {
			// Handle the case when a is false
			return "false";
		}	
	}
	//비밀번호 재설정 로직
	public String generateRandomPassword() {
		// Define the characters that can be used in the password
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// Set the desired length of the password
		int length = 8;

		// Create a StringBuilder to store the generated password
		StringBuilder sb = new StringBuilder();

		// Generate random characters from the defined set
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();

	}
// ------------------------------ 비밀번호 찾기 로직 끝 ----------------------------------	
}
