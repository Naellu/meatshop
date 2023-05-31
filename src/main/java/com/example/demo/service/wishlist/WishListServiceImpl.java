package com.example.demo.service.wishlist;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.demo.domain.*;
import com.example.demo.mapper.wishlist.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

	private final WishListMapper wishMapper;
	
	@Override
	public List<WishListView> getViewList(String memberId) {
		return wishMapper.getAllView(memberId);
	}

	@Override
	public Boolean remove(WishList wishList) {
		Integer cnt = wishMapper.deleteList(wishList);
		
		return cnt == 1;
	}

}
