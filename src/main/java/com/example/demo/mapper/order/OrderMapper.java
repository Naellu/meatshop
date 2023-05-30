package com.example.demo.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.Members;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;

@Mapper
public interface OrderMapper {

	// 주문상세 생성
	@Insert("""
			INSERT INTO orderitems (order_id, product_id, quantity, order_price)
			VALUES (#{orderId}, #{productId}, #{quantity}, #{orderPrice})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	Integer saveOrderItems(OrderItem orderItem);

	// 주문 생성
	@Insert("""
			INSERT INTO orders (member_id, status, total_price)
			VALUES (#{memberId}, #{status}, #{totalPrice})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	Integer saveOrder(Order order);

	@Select("""
   			SELECT id
   			FROM order
   			WHERE member_id = #{memberId}
			""")
	Integer findOne(String memberId);
	
	// 회원 모든정보
	@Select("""
			SELECT *
			FROM members
			WHERE id = #{id}
			""")
	Members selectByMemberId(String id);
	
	// 상품 이름
	@Select("""
			SELECT product_name
			FROM products
			WHERE product_id = #{product_id}
			""")
	String selectByProductId(Integer productId);

	// 상품 가격
	@Select("""
			SELECT price
			FROM products
			WHERE product_id = #{product_id}
			""")
	double findPrice(Integer productId);

	// 회원의 모든 주문내역
	@Select("""
   			SELECT *
   			FROM orders
   			WHERE id = #{memberId}
			""")
	List<Order> findAllByMemberId(String memberId);


	@Select("""
   			SELECT 
   				id,
   				member_Id memberId,
   				status,
   				created,
   				total_price totalPrice
   			FROM orders
			""")
	List<Order> findAll();

	
	@Delete("""
			<script>
			DELETE FROM cartitems
			WHERE product_id IN
			<foreach item='item' index='index' collection='productIds' open='(' separator=',' close=')'>
				#{item}
			</foreach>
			AND cart_id IN (SELECT id FROM carts WHERE member_id = #{memberId})
			</script>
			""")
	void deleteItemsFromCart(@Param("productIds") List<Integer> productIds, @Param("memberId") String memberId);

}
