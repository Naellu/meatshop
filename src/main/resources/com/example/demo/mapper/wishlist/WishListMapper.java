package com.example.demo.mapper.wishlist;

import com.example.demo.domain.WishList;
import com.example.demo.domain.WishListView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface WishListMapper {

	public List<WishListView> getAllView(String memberId);

	public Integer deleteList(WishList wishList);

	public Integer insert(WishList wishList);

	public Set<Integer> getLikedProductId(String memberId);

	public List<String> getLikedMemberId(Integer productId);
}
