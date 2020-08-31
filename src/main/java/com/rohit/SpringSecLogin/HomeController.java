package com.rohit.SpringSecLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@RequestMapping("/")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "Hello Admin";
	}
	
	@RequestMapping(value="/authenticate", method =RequestMethod.POST)
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authReq) {
		
		try {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword()));
	}
	catch(Exception e) {
		System.out.println("Invalid UserName or Password");
	}
	final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authReq.getUsername());
	String jwt = jwtUtil.generateToken(userDetails);
	
	return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}	
	
}
