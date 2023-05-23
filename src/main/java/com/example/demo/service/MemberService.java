package com.example.demo.service;

import java.util.List;

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
		String plain = member.getMember_password();
		member.setMember_password(passwordEncoder.encode(plain));
		
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
}
