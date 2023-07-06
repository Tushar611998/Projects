package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.getUserByName(username);
		
		
		if(user== null) {
			throw new UsernameNotFoundException("The invalid Creditentials");
		}
		CustomUserDetails uDetails = new CustomUserDetails(user);
		return uDetails;
	}

}
