package com.example.demo.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.Members;
import com.example.demo.domain.Product;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderItem;
import com.example.demo.domain.order.dto.OrderDto;

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

	// 회원id로 주문id 찾기
	@Select("""
   			SELECT id
   			FROM order
   			WHERE member_id = #{memberId}
			""")
	Integer findOne(String memberId);
	
	// 주문id로 주문정보 가져오기
	@Select("""
			SELECT *
			FROM orders
			WHERE id = #{id}
			""")
	OrderDto findById(Integer id);
	
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
	
	@Select("""
			SELECT 
				product_id productId,
				stock_quantity stockQuantity
			FROM products
			WHERE product_id = #{productId}
			""")
	Product selectAllByProductId(Integer productId);
	
	// 상품 개수 반영
	@Update("""
			UPDATE products
			SET
				stock_quantity = #{quantity}
			WHERE product_id = #{productId}
			""")
	Integer updateProductQuantity(Integer productId, Integer quantity);
	
	@Select("""
			SELECT 
				id,
				product_id productId,
				order_id orderId,
				quantity,
				order_price orderPrice
			FROM orderitems
			WHERE order_id = #{orderId};
			""")
	List<OrderItem> findAllOrderItemByOrderId(Integer orderId);

	// 회원의 모든 주문내역
	@Select("""
   			SELECT 
				o.id,
			    o.member_id,
			    p.product_name productName,
			    o.total_price,
			    DATE(o.created) created,
			    o.status status
			FROM orders as o
			LEFT JOIN orderitems as oi ON o.id = oi.order_id
			INNER JOIN products as p ON p.product_id = oi.product_id
			WHERE o.member_id = #{memberId}
			ORDER BY o.id DESC;
			""")
	@ResultMap("OrderItemMap")
	List<OrderDto> findAllByMemberId(String memberId);


	// 전체 주문목록 보기
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

	// 관리자 전체 주문목록 보기
	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			
   			SELECT 
				o.id,
			    o.member_id,
			    p.product_name productName,
			    o.total_price,
			    DATE(o.created) created,
			    o.status status
			FROM orders as o
			LEFT JOIN orderitems as oi ON o.id = oi.order_id
			INNER JOIN products as p ON p.product_id = oi.product_id
			
			<where>
				<if test="(type eq 'all') or (type eq 'member')">
					member_id LIKE #{pattern}
				</if>
				<if test="(type eq 'all') or (type eq 'product')">
					OR p.product_name LIKE #{pattern}
				</if>
			</where>
			
			ORDER BY o.id DESC
			LIMIT #{startIndex}, #{rowPerPage}
			
			</script>
			""")
	@ResultMap("OrderItemMap")
	List<OrderDto> findAllOrders(Integer startIndex, Integer rowPerPage, String search, String type);

	// 페이징을 위한 총 주문 개수
	@Select("""
			<script>
			<bind name="pattern" value="'%' + search + '%'" />
			SELECT COUNT(*)
			FROM orders as o
			LEFT JOIN orderitems as oi ON o.id = oi.order_id
			INNER JOIN products as p ON p.product_id = oi.product_id
			
			<where>
				<if test="(type eq 'all') or (type eq 'member')">
					member_id LIKE #{pattern}
				</if>
				<if test="(type eq 'all') or (type eq 'product')">
					OR p.product_name LIKE #{pattern}
				</if>
			</where>
			
			</script>
			""")
	Integer countAll(String search, String type);

	// 장바구니에 담아둔 상품 삭제
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

	
	@Update("""
			UPDATE orders 
			SET 
				status = #{status}
			WHERE id = #{orderId};
			""")
	boolean updateStatus(Integer orderId, String status);

	
	
	
}
