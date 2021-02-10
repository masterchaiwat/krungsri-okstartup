package com.reg.ok.okstartup.services;

import org.springframework.stereotype.Service;

import com.reg.ok.okstartup.model.User;


public interface UserService {

	public abstract void save(User user);
	
}
