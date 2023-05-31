package com.example.demo.service.cart;

import java.util.List;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.cart.dto.CartItemDto;

public interface CartService {


    int makeOrderByCartItems(String memberId, Integer productId, Integer quantity, Double price);

    Cart findCartByMemberId(String memberId);

    List<CartItem> findAllItems(Cart cart);
    
    boolean deleteCartItem(CartItemDto cartItemDto);
    
    Integer updateCartItemQuantity(CartItemDto cartItemDto);
}
