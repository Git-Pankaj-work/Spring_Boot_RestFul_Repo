package com.myfriendapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfriendapp.entity.Friend;
import com.myfriendapp.exception.FriendNotfoundException;
import com.myfriendapp.repository.FriendRepository;

@Service
public class FriendServiceImpl implements FriendService {

	private static final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

	@Autowired
	private FriendRepository friendRepository;

	@Override
	public String saveFriend(Friend friend) {
		log.info("Inside FriendServiceImpl.addFriend method");
		log.debug("Input data:{}", friend);
		Friend f = friendRepository.save(friend);
		log.debug("Response data:{}", friend);
		return "friend with name :" + f.getName() + " hasa been saved successfully with id =" + f.getId();
	}

	@Override
	public Friend findFriendById(Integer id) {
		log.info("Inside FriendServiceImpl.getFriendById method");
		 Friend friend = friendRepository.findById(id).orElseThrow(() -> new FriendNotfoundException("Friend not found for this id :: " + id));
		 return friend;
	}

	@Override
	public List<Friend> findAllFriends() {
		return friendRepository.findAll();
	}

	@Override
	public Friend updateFriend(Friend friend) {
		return friendRepository.save(friend);
	}

	@Override
	public void deleteFriendById(Integer id) {
		Friend myFriend = friendRepository.findById(id).get();
		friendRepository.delete(myFriend);
	}
}
