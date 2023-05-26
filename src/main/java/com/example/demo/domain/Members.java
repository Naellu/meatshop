package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
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
}
