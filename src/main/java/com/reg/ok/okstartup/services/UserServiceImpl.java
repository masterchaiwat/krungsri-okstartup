package com.reg.ok.okstartup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reg.ok.okstartup.model.User;
import com.reg.ok.okstartup.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

}
