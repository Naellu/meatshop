package com.example.demo.mapper.cart;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("""
            INSERT INTO carts (member_id)
            VALUES (#{memberId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer saveCart(Cart cart);

    @Insert("""
            INSERT INTO cartitems (cart_id, product_id, quantity, product_price)
            VALUES (#{cartId}, #{productId}, #{quantity}, #{productPrice})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer saveCartItems(CartItem cartItem);

    @Update("""
            UPDATE cartitems
            SET
                quantity = #{quantity}
            WHERE id = #{id}
            """)
    Integer updateItems(CartItem cartItem);

    @Select("""
            SELECT *
            FROM cartitems
            WHERE cart_id = #{cartId} AND product_id = #{productId}
            """)
    CartItem findByCartIdAndProductId(Integer cartId, Integer productId);

    @Select("""
            SELECT *
            FROM carts
            WHERE member_id = #{memberId}
            """)
    Cart findByMemberId(String memberId);

    @Select("""
            SELECT
				ci.id,
				ci.cart_id as cartId,
				ci.product_id as productId,
				ci.quantity,
				ci.product_price as productPrice,
			    p.product_name as productName
			FROM cartitems as ci
			LEFT JOIN products as p ON ci.product_id = p.product_id
			WHERE cart_id = #{cartId};
            """)
    List<CartItem> findAllCartItems(Integer cartId);
}
