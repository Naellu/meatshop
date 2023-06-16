package com.example.demo.domain.payment;

import lombok.Data;

@Data
public class CancelDataDto {
	private String impUid;
	private String merchantUid;
}
