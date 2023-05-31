package com.example.demo.domain.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartItemDto {
	private Integer id;
	private String memberId;
    private Double price;
    private Integer productId;
    private Integer quantity;
}
