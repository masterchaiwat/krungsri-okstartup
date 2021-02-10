package com.reg.ok.okstartup.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reg.ok.okstartup.dto.UserJson;
import com.reg.ok.okstartup.model.RegisterInfo;
import com.reg.ok.okstartup.model.User;
import com.reg.ok.okstartup.repository.RegisterInfoRepository;
import com.reg.ok.okstartup.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisterInfoRepository registerInfoRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
	
	public ResponseEntity<Object> save(UserJson userJson) {
		//validate duplicate username
		User user  = userRepository.findByUsername(userJson.getUsername());
		if(user != null)
			return new ResponseEntity<>("duplicate username", HttpStatus.PROCESSING);
			//throw new DuplicateUserException("duplicate username " + userJson.getUsername());
		
		user = new User();
		user.setUsername(userJson.getUsername());
		user.setPassword(bcryptEncoder.encode(userJson.getPassword()));
		userRepository.save(user);
		
		RegisterInfo regisInfo = new RegisterInfo();
		
		regisInfo.setPhone(userJson.getPhone());
		
		Date d = new Date();
		regisInfo.setRegisterDate(d);
		
		String lastFourDigit = userJson.getPhone() != null ? userJson.getPhone().substring(userJson.getPhone().length() - 4) : "";
		regisInfo.setReferenceCode(new SimpleDateFormat("yyyyMMdd").format(d) + lastFourDigit);
		
		regisInfo.setEmail(userJson.getEmail());
		regisInfo.setAddress(userJson.getAddress());
		regisInfo.setFirstName(userJson.getFirstname());
		regisInfo.setLastName(userJson.getLastname());
		regisInfo.setUser(user);
		
		String memberType = "";
		int salary = userJson.getSalary().intValue();
		if(salary > 50000) {
			memberType = "Platinum";
		}
		else if(salary >= 30000) {
			memberType = "Gold";
		}
		else {
			memberType = "Silver";
		}
		regisInfo.setMemberType(memberType);
		return new ResponseEntity<>("", HttpStatus.OK);
		
	}

}
