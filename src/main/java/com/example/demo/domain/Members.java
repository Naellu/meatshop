package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Members {
	private String id;
	private String member_password;
	private String member_name;
	private String member_birthday;
	private String member_email;
	private String member_address;
	private String member_phone_number;
	private String member_created;

//	private LocalDateTime inserted;
//	private String oldPassword;
	private List<String> authority;
}
