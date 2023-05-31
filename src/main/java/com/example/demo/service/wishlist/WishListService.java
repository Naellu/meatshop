package com.example.demo.service.wishlist;

import java.util.*;

import com.example.demo.domain.*;

public interface WishListService {

	public List<WishListView> getViewList(String memberId);

	public Boolean remove(WishList wishList);
}
