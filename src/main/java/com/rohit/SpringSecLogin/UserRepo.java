package com.rohit.SpringSecLogin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

	User findByUsername(String username);
	
}
