package com.myfriendapp.service;

import java.util.List;

import com.myfriendapp.entity.Friend;

public interface FriendService {
	
	public String saveFriend(Friend friend);
	public Friend findFriendById(Integer id);
	public List<Friend> findAllFriends();
	public Friend updateFriend(Friend friend);
	public void deleteFriendById(Integer id);

}
