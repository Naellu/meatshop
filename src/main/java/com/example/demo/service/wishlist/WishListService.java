package com.example.demo.service.wishlist;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.core.*;

import com.example.demo.domain.*;

public interface WishListService {

	public List<WishListView> getViewList(String memberId);

	public Boolean remove(WishList wishList);

	public Map<String, Object> like(Authentication authentication, WishList wishList);
}
