package com.example.demo.domain.order;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<String> fromName(Status[] names) {
		List<String> titles = new ArrayList();
		for(Status status : names) {
			titles.add(status.getTitle());
		}
		return titles;
	}
}
