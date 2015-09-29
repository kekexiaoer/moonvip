package com.tz.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.tz.core.TmConstant;
import com.tz.core.tag.ITmContextProvider;
import com.tz.model.User;


public class BaseController {
	
	@Autowired
	protected ITmContextProvider contextProvider;
	
	public User getUser(){
		return (User)contextProvider.getSessionAttribute(TmConstant.SESSION_USER_KEY);
	}
	
	public Integer getUserId(){
		return (Integer)contextProvider.getSessionAttribute(TmConstant.SESSION_USER_KEY_ID);
	}
}