package com.example.demo.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItemDto {
    private String memberId;
    private Double price;
    private Integer productId;
    private Integer quantity;
    
    private boolean fromCart;
}
