package com.reg.ok.okstartup.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reg.ok.okstartup.dto.JwtRequest;
import com.reg.ok.okstartup.dto.JwtResponse;
import com.reg.ok.okstartup.dto.RegisterInfoResponse;
import com.reg.ok.okstartup.dto.UserJson;
import com.reg.ok.okstartup.model.RegisterInfo;
import com.reg.ok.okstartup.model.User;
import com.reg.ok.okstartup.security.JwtTokenUtil;
import com.reg.ok.okstartup.services.JwtUserDetailsService;

@RestController
public class RegistrationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
    private ModelMapper modelMapper;
	
	@Bean
	public void modelMapper() {
	    this.modelMapper = new ModelMapper();
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserJson user) throws Exception {
		userDetailsService.save(user);
		return ResponseEntity.ok("");
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	public ResponseEntity<?> getUserInfo(Principal principal) throws Exception {
		User user = userDetailsService.getUser(principal.getName());
		return ResponseEntity.ok(modelMapper.map(user.getRegisterInfo(), RegisterInfoResponse.class));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
