package com.example.demo.service.cart;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;

import java.util.List;

public interface CartService {


    int makeOrderByCartItems(String memberId, Integer productId, Integer quantity, Double price);

    Cart findCartByMemberId(String memberId);

    List<CartItem> findAllItems(Cart cart);
}
