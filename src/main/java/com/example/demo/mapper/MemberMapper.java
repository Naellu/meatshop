package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.Members;

@Mapper
public interface MemberMapper {

	@Insert("""
		    INSERT INTO members (id,
			member_password,
			member_name,
			member_birthday,
			member_email,
			member_address,
			member_phone_number)
			VALUES(
			#{id},
			#{member_password},
			#{member_name},
			#{member_birthday},
			#{member_email},
			#{member_address},
			#{member_phone_number}
			)

						""")

	int insert(Members member);

	
	
	@Select("""
			SELECT * FROM members
			WHERE id = #{id}
			""")
	Members selectId(String id);

	@Select("""
			SELECT * FROM members
			ORDER BY member_created DESC
			
			""")

	List<Members> selectALL();

}
