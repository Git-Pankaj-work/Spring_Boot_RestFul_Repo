package com.myfriendapp.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfriendapp.entity.Friend;
import com.myfriendapp.restcontroller.FriendRestController;
import com.myfriendapp.service.FriendServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FriendRestController.class, secure = false) // This will load full Spring application context is started but without the server.
public class FriendRestControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@SuppressWarnings("unused")
	@Autowired
	private FriendRestController friendRestController;

	@MockBean
	private FriendServiceImpl friendService;

	/*@Test
	public void testCreateFriend() throws Exception {

		Friend mockFriend = new Friend();
		mockFriend.setId(1);
		mockFriend.setName("name1");
		mockFriend.setEmail("email1");
		mockFriend.setPhone("phone1");

		String inputInJson = this.mapToJson(mockFriend);

		String URI = "/saveFriend_1";

		Mockito.when(friendService.saveFriend(Mockito.any(Friend.class))).thenReturn("3");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}*/

	@Test
	public void testGetFriendById() throws Exception {
		
		Friend mockFriend = new Friend();
		mockFriend.setId(1);
		mockFriend.setName("pankaj");
		mockFriend.setEmail("pankajkumar.p2@gmail.com");
		mockFriend.setPhone("12345");
		
		Mockito.when(friendService.findFriendById(Mockito.anyInt())).thenReturn(mockFriend);
		
		String URI = "/getFriendById/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockFriend);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);
		JSONAssert.assertEquals(expectedJson, outputInJson, false);
	}

	@Test
	public void testGetAllFriends() throws Exception {

		Friend mockFriend = new Friend();
		mockFriend.setId(3);
		mockFriend.setName("pankaj");
		mockFriend.setEmail("pankajkumar.p2@gmail.com");
		mockFriend.setPhone("12345");

		Friend mockFriend1 = new Friend();
		mockFriend.setId(4);
		mockFriend.setName("yogesh");
		mockFriend.setEmail("yogesh@gmail.com");
		mockFriend.setPhone("1234567");
		
		List<Friend> friendList = new ArrayList<>();
		friendList.add(mockFriend);
		friendList.add(mockFriend1);

		Mockito.when(friendService.findAllFriends()).thenReturn(friendList);

		String URI = "/viewAllFriends";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(friendList);
		String outputInJson = result.getResponse().getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);
	}

/*	@Test
	public void testGetFriendByEmail() throws Exception {
	
		Friend mockFriend = new Friend();
		mockFriend.setId(1);
		mockFriend.setName("pankaj");
		mockFriend.setEmail("pankajkumar.p2@gmail.com");
		mockFriend.setPhone("12345");


		String expectedJson = this.mapToJson(mockTicket);

		Mockito.when(ticketBookingService.getTicketByEmail(Mockito.anyString())).thenReturn(mockTicket);

		String URI = "/api/tickets/email/martin.s2017@gmail.com";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);

	}*/

	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
