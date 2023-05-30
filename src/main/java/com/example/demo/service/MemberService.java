package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.domain.Members;
import com.example.demo.mapper.MemberMapper;

@Service
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

	}

