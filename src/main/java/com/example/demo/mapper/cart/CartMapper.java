package com.example.demo.mapper.cart;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CartMapper {

	// 장바구니 정보 저장
    @Insert("""
            INSERT INTO carts (member_id)
            VALUES (#{memberId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer saveCart(Cart cart);

    // 장바구니 항목 저장
    @Insert("""
            INSERT INTO cartitems (cart_id, product_id, quantity, product_price)
            VALUES (#{cartId}, #{productId}, #{quantity}, #{productPrice})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer saveCartItems(CartItem cartItem);

    // 장바구니 항목 수량 갱신
    @Update("""
            UPDATE cartitems
            SET
                quantity = #{quantity}
            WHERE id = #{id}
            """)
    Integer updateItems(CartItem cartItem);

    // 장바구니에 상품이 이미 들어있는지 가져오기
    @Select("""
            SELECT *
            FROM cartitems
            WHERE cart_id = #{cartId} AND product_id = #{productId}
            """)
    CartItem findByCartIdAndProductId(Integer cartId, Integer productId);
    
    // cartItem의 id로 장바구니 항목 가져오기
    @Select("""
            SELECT
                id,
                cart_id,
                product_id,
                quantity,
                product_price
            FROM cartitems
            WHERE id = #{id}
            """)
    CartItem findByCartItemId(Integer Id);

    // 장바구니 가져오기
    @Select("""
            SELECT *
            FROM carts
            WHERE member_id = #{memberId}
            """)
    Cart findByMemberId(String memberId);

    // 장바구니에 들어있는 상품 모두 가져오기
    @Select("""
            SELECT
				ci.id,
				ci.cart_id,
				ci.product_id,
				ci.quantity,
				ci.product_price,
			    p.product_name as productName
			FROM cartitems as ci
			LEFT JOIN products as p ON ci.product_id = p.product_id
			WHERE cart_id = #{cartId};
            """)
    @ResultMap("CartItemsMap")
    List<CartItem> findAllCartItems(Integer cartId);
    
    // 장바구니 항목 삭제
    @Delete("""
    		DELETE cartitems
			FROM cartitems
			LEFT JOIN carts ON carts.id = cartitems.cart_id
			WHERE member_id = #{memberId} AND product_id = #{productId};
    		""")
    @ResultMap("CartItemsMap")
    boolean deleteCartItem(String memberId, Integer productId);
}
