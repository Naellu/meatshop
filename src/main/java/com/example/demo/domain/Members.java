package com.example.demo.domain;

import java.util.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Members {
	private String id;
	private String password;
	private String name;
	private String birthday;
	private String email;
	private String address;
	private String phoneNumber;
	private String created;

//	private LocalDateTime inserted;
	private String oldPassword;
	private List<String> authority;
	private String newPassword;
	
	// 카카오 여부
	private String oauth;
}
