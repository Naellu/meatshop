package com.example.demo.mapper.wishlist;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.domain.*;

@Mapper
public interface WishListMapper {

	public List<WishListView> getAllView(String memberId);

	public Integer deleteList(WishList wishList);
}
