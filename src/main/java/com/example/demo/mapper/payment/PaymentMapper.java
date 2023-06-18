package com.example.demo.mapper.payment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.siot.IamportRestClient.response.Payment;

@Mapper
public interface PaymentMapper {

	// 결제번호로 결제 정보 가져오기
	@Select("""
			SELECT *
			FROM payments
			WHERE imp_uid = #{impUid}
			""")
	Integer findByImpUid(String impUid);
	
	// 결제정보 저장하기
	@Insert("""
			INSERT INTO 
				payments (
					imp_uid, 
					merchant_uid, 
					name,
					amount,
					buyer_name,
					status,
					paid_at
				)
				
			VALUES 
				(
					#{imp_uid},
					#{merchant_uid},
					#{name},
					#{amount},
					#{buyer_name},
					#{status},
					#{paid_at}
				)
			""")
	Integer savePaymentInfo(Payment payment);
	
	
	@Update("""
			UPDATE payments
			SET
				status = #{status}
			WHERE 
				imp_uid = #{imp_uid} AND
				merchant_uid = #{merchant_uid}
			""")
	Integer changePaymentStatus(Payment payment);

	
}
