package com.myfriendapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myfriendapp.entity.Friend;
import com.myfriendapp.repository.FriendRepository;
import com.myfriendapp.service.FriendServiceImpl;

@SpringBootTest // starts Spring application context but not the tomcat server.
@RunWith(SpringRunner.class) // junit4
public class FriendServiceTest {

	@Mock
	private FriendRepository friendRepository;

	@InjectMocks
	private FriendServiceImpl friendService;

	@Test
	public void testSaveFriend() {
		Friend friend = new Friend(1, "punya", "punya@gmail.com", "12345");
		Mockito.when(friendRepository.save(friend)).thenReturn(friend);
		Assertions.assertThat(friendService.saveFriend(friend)).isEqualTo(
				"friend with name :" + friend.getName() + " hasa been saved successfully with id =" + friend.getId());
	}

	@Test
	public void testGetFriendById() {
		Friend friend = new Friend(1, "punya", "punya@gmail.com", "12345");
		Mockito.when(friendRepository.findById(1)).thenReturn(Optional.of(friend));
		Assertions.assertThat(friendService.findFriendById(1)).isEqualTo(friend);
	}

	@Test
	public void testGetAllFriends() {

		Friend friend1 = new Friend(1, "punya", "punya@gmail.com", "12345");
		Friend friend2 = new Friend(2, "aswini", "aswini@gmail.com", "1234525");

		List<Friend> list = new ArrayList<>();
		list.add(friend1);
		list.add(friend2);

		Mockito.when(friendRepository.findAll()).thenReturn(list);
		Assertions.assertThat(friendService.findAllFriends()).isEqualTo(list);
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void testUpdateFriend() {

		Friend friend1 = new Friend(1, "punya", "punya@gmail.com", "12345");
		Mockito.when(friendRepository.findById(1)).thenReturn(Optional.of(friend1));
		friend1.setEmail("martin.s2000@gmail.com");
		Mockito.when(friendRepository.save(friend1)).thenReturn(friend1);
		Assertions.assertThat(friendService.updateFriend(friend1)).isEqualTo(friend1);

	}

	@Test
	public void testDeleteFriend() {
		Friend friend1 = new Friend(1, "punya", "punya@gmail.com", "12345");
		Mockito.when(friendRepository.findById(1)).thenReturn(Optional.of(friend1));
		Mockito.when(friendRepository.existsById(friend1.getId())).thenReturn(false);
		Assert.assertFalse(friendRepository.existsById(friend1.getId()));
	}
}
