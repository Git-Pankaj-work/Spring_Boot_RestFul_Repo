package com.myfriendapp.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfriendapp.entity.Friend;
import com.myfriendapp.exception.FriendNotfoundException;
import com.myfriendapp.service.FriendService;

@RestController
@RequestMapping("/api/rest")
public class FriendRestController {

	private static final Logger log = LoggerFactory.getLogger(FriendRestController.class);

	@Autowired
	FriendService friendService;


	// ################################################################
	// INSERT Friend Operation (way-01)
	// ################################################################

	//@RequestMapping("//saveFriend_1")
	@GetMapping("/saveFriend_1")
	public String saveFrind(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone) {

		log.info("Inside FriendController.saveMyFrind");

		Friend myfriend = new Friend();
		myfriend.setName(name);
		myfriend.setEmail(email);
		myfriend.setPhone(phone);

		log.debug("Input data:{}", myfriend);

		String res = friendService.saveFriend(myfriend);
		log.info("Response  data: {}", res);

		return "record saved successfully";
	}

	// ################################################################
	// INSERT Friend Operation (way-02)
	// ################################################################
	
	//@RequestMapping(value = "/save_my_friend_2", method = RequestMethod.POST)
	@PostMapping(value = "/saveFriend_1")
	public String saveMyFrind2(@Valid @RequestBody Friend friend) {
		log.info("Inside FriendRestController.saveMyFrind2 ");
			String res = friendService.saveFriend(friend);
			return res;
	}
	
	// ################################################################
	// SELECT Operation
	// ################################################################

	@RequestMapping("/viewAllFriends")
	public List<Friend> viewFriends() {
		log.info("Inside FriendController.view_all_friends");
		List<Friend> list = friendService.findAllFriends();
		return list;
	}
	
	@RequestMapping("/getFriendById/{id}")
	public Friend getFriendbyId(@PathVariable int id) {
		Friend friend=friendService.findFriendById(id);
		return friend;
	}
	
	@RequestMapping("/getFriendById2/{id}")
	public ResponseEntity<Friend> getFriendbyId2(@PathVariable int id) {
		Friend friend=friendService.findFriendById(id);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	

	// ################################################################
	// UPDATE Operation
	// ################################################################

	@RequestMapping(value = "/editsave", method = RequestMethod.PUT)
	//@PutMapping(value = "/editsave")
	public Friend editsave(@Valid  @RequestBody  Friend friend) {
		log.info("Inside FriendController.editsave");
		return friendService.updateFriend(friend);
	}

	// ################################################################
	// DELETE Operation
	// ################################################################

	@RequestMapping(value = "/deletefriend/{id}", method = RequestMethod.DELETE)
	//@DeleteMapping(value = "/deletefriend/{id}")
	public String delete(@PathVariable int id) {
		log.info("Inside FriendController.deletefriend");
		log.info("added this line to test git");
		friendService.deleteFriendById(id);
		return "friend deleted successfully";
 }
}
