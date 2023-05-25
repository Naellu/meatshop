package com.example.demo.domain;

import java.util.*;

import lombok.*;

@Data
public class PubRequest {
	private List<String> openIds;
	private List<String> ids;
}
