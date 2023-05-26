package com.example.demo.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderItemDto {
    private String memberId;
    private List<Integer> productIdList;
    private List<Integer> quantities;
}
