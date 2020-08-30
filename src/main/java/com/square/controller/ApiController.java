package com.square.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.square.payload.JwtRequest;
import com.square.payload.JwtResponse;
import com.square.security.config.JwtUtil;
import com.square.security.service.MyUserDetailsService;

@RestController
public class ApiController
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtils;
	
    @PostMapping("/authenticate")
    public ResponseEntity<?> home(@RequestBody JwtRequest authenticationRequest) throws Exception{
       
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect ", e);
		}
    	
    	final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
    	
    	final String jwt = jwtUtils.generateToken(userDetails);
    	
    	return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
