package com.example.demo.domain.order;

import org.apache.ibatis.type.MappedTypes;

import lombok.ToString;

public enum Status{
	CREATED("주문완료"),
	PREPARE("배송준비"),
	DELIVERY("배송중"),
	COMPLETE("배송완료"),
	CONFIRM_PURCHASE("구매확정"), 
	CANCEL("주문취소");
	
	private String title;
	
	Status(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public static Status fromTitle(String title) {
		for(Status status : values()) {
			if(status.title.equals(title)) {
				return status;
			}
		}
		throw new IllegalArgumentException("enum 상수가 아닙니다 " + Status.class.getCanonicalName() + "." + title);
	}
}
