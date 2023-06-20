package com.example.demo.security;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.*;
import com.example.demo.service.*;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private MemberService service;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Members member = mapper.selectId(username);
		System.out.println("this is security" + member);
		if(member == null) {
			throw new UsernameNotFoundException(username + "해당 회원 조회 불가");
		}
		
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		for(String auth : member.getAuthority()) {
			authorityList.add(new SimpleGrantedAuthority(auth)); 
		}
		
		UserDetails user = User.builder()
				.username(member.getId())
				.password(member.getPassword())
				.authorities(List.of())
				.authorities(member.getAuthority().stream().map(SimpleGrantedAuthority::new).toList())
				.build();
				
		System.out.println(user);
		return user;
	}
}
