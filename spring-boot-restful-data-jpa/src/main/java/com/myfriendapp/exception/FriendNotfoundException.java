package com.myfriendapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FriendNotfoundException extends RuntimeException{

	private static final long serialVersionUID = 6154718164324239208L;
	
	public FriendNotfoundException(String msg) {
		super(msg);
	}
}
