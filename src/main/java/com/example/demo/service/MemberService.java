package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Members;
import com.example.demo.mapper.MemberMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	
	
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
		
		if(!member.getPassword().isBlank()) {
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
		
		if (passwordEncoder.matches(member.getPassword(),oldMember.getPassword())) {
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

	public Map<String,Object> checkId(String id) {
	Members member = mapper.selectId(id);
		
	return Map.of("available",member == null);
	}
	
	public Map<String,Object> checkEmail(String id) {
		Members member = mapper.selectEmail(id);
			
		return Map.of("available",member == null);
		}

	
	
	
	
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

	}

