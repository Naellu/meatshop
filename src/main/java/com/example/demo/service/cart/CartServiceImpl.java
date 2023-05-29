package com.example.demo.service.cart;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.mapper.cart.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartMapper cartMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int makeOrderByCartItems(String memberId, Integer productId, Integer quantity, Double price) {

		// 회원id로 장바구니 조회
		Cart cart = cartMapper.findByMemberId(memberId);

		// 없으면 장바구니 생성
		if (cart == null) {
			cart = Cart.createCart(memberId);
			cartMapper.saveCart(cart);
		}

		CartItem cartItem = cartMapper.findByCartIdAndProductId(cart.getId(), productId);

		// 장바구니에 해당 cartitem이 없다면
		if (cartItem == null) {

			int productPrice = price.intValue();
			// 장바구니에 장바구니항목 추가
			cartItem = CartItem.createCartItem(productId, quantity, productPrice);
			cartItem.setCartId(cart.getId());
			cartMapper.saveCartItems(cartItem);

		} else {
			// 있으면 수량만 증가
			CartItem updateItem = cartItem;
			updateItem.addCount(quantity);
			cartMapper.updateItems(updateItem);
		}

		return 1;
	}

	@Override
	public Cart findCartByMemberId(String memberId) {
		return cartMapper.findByMemberId(memberId);
	}


	@Override
	public List<CartItem> findAllItems(Cart cart) {
		return cartMapper.findAllCartItems(cart.getId());
	}
}
