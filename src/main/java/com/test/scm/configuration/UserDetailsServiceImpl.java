package com.test.scm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.scm.dao.UserRepository;
import com.test.scm.entity.User;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userByUserEmail = userRepository.getUserByUserEmail(username);
		if(userByUserEmail==null) {
			throw new UsernameNotFoundException("userEmail is not found");
		}
		
		UserDetailsImpl userDetailsImpl=new UserDetailsImpl(userByUserEmail);
		return userDetailsImpl;
	}

}
