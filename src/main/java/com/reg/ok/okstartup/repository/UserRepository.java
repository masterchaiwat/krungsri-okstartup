package com.reg.ok.okstartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reg.ok.okstartup.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
