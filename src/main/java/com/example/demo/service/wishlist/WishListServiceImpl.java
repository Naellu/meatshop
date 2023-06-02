package com.example.demo.service.wishlist;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> like(Authentication authentication, WishList wishList) {
		Map<String, Object> result = new HashMap<>();

		result.put("like", false);

		wishList.setMemberId(authentication.getName());
		Integer deleteCnt = wishMapper.deleteList(wishList);
		
		if (deleteCnt != 1) {
			Integer insertCnt = wishMapper.insert(wishList);
			result.put("like", true);
		}

		return result;
	}

}
